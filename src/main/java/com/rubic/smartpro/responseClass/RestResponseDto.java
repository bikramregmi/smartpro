package com.rubic.smartpro.responseClass;


import com.rubic.smartpro.enumConstants.ResponseStatus;

public class RestResponseDto {

    private String status;

    private ResponseStatus responseStatus;

    private String message;

    private Object detail;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }
}
