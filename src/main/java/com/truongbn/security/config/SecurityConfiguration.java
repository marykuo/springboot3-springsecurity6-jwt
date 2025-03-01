package com.truongbn.security.config;

import com.truongbn.security.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService;

    private static final String[] AUTH_WHITELIST = {
            // Health Check APIs
            "/",
            "/actuator/**",
            // OpenAPI 3.0
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            // Public APIs
            "/public/v*/**",
            // Authentication
            "/api/v*/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationProvider authenticationProvider,
            CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
            AccessDeniedHandler accessDeniedHandler
    ) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers(AUTH_WHITELIST).permitAll();
                    request.anyRequest().authenticated();
                })
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    @Profile("prod")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * This encoder allows the database to store passwords in plain text, which is insecure.
     * notice: For development or testing purposes only.
     */
    @Bean
    @Profile("!prod")
    public PasswordEncoder insecurePasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("bcrypt", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());
        return passwordEncoder;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Authorized but insufficient permissions
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("無權使用此功能");
        };
    }
}
