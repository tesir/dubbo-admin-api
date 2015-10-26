package com.dtdream.spidernet.exception;

import org.springframework.http.HttpStatus;
import java.util.Map;

public class SpiderException extends RuntimeException {

    private final HttpStatus status;
    protected Map<String, Object> extraInfo;

    public SpiderException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public SpiderException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public SpiderException(String message, HttpStatus status, Map<String,Object> extraInformation) {
        super(message);
        this.status = status;
        this.extraInfo = extraInformation;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }
}
