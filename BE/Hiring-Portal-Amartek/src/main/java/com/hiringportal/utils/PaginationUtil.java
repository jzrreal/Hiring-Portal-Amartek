package com.hiringportal.utils;

import com.hiringportal.dto.PaginationResultResponse;

import java.util.List;

public class PaginationUtil {

    public static <T> PaginationResultResponse<T> createResultPageResponse(T result, Integer element, Integer page, Integer currentPage){
        PaginationResultResponse<T> results = new PaginationResultResponse<>();
        results.setPage(page);
        results.setElements(element);
        results.setResult(result);
        results.setCurrentPage(currentPage);

        return results;
    }
}
