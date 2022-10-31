package com.happyfleet.app.api.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestResponse {

    public static final String SUCCESS = "success";

    private final String status;
    private final Object data;

    public RestResponse(String status, Object data) {
        this.status = status;
        this.data = data;
    }

    public RestResponse(Object data) {
        this.status = SUCCESS;
        this.data = data;
    }
}
