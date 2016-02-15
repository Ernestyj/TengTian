package com.eugene.exception;

/**
 * Created by DCLab on 12/22/2015.
 */
public class RestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Long resourceId;

    public ErrorCode errorCode;

    public  RestException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public RestException(String message, ErrorCode errorCode, Long id) {
        super(message);
        this.errorCode = errorCode;
        this.resourceId = id;
    }

    public Long getResourceId() {
        return resourceId;
    }
}
