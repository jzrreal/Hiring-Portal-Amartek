package com.hiringportal.dto;

import lombok.Data;

@Data
public class WebResponse <T> {

    private String message;
    private T data;
    private Integer status;
}
