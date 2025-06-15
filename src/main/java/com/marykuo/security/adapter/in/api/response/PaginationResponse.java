package com.marykuo.security.adapter.in.api.response;

import lombok.Getter;

import java.util.List;

@Getter
public class PaginationResponse<T> extends BaseResponse {
    protected List<T> data;
    protected Long totalElements;
    protected Integer totalPages;
    protected Integer currentPage;
    protected Integer limitPerPage;

    public PaginationResponse(T data) {
        super(true, "200", "success");
        this.data = List.of(data);
        this.totalElements = 1L;
        this.totalPages = 1;
        this.currentPage = 1;
        this.limitPerPage = 1;
    }

    public PaginationResponse(List<T> dataList, Long totalElements, Integer totalPages, Integer currentPage, Integer limitPerPage) {
        super(true, "200", "success");
        this.data = dataList;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.limitPerPage = limitPerPage;
    }
}
