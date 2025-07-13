package com.cengzayhn.mini_banking_api.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String errorCode;
    private Object[] args;

    public BaseException(HttpStatus httpStatus, String errorCode) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public BaseException(String message, HttpStatus httpStatus, String errorCode, Object[] args) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.args = args;
    }

    public String getMessageKey() {
        return ErrorCodeLookUp.getMessageKey(errorCode);
    }
}