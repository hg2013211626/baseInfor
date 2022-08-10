package com.magnificent.exception;

public class InforException extends RuntimeException {

    private String errorCode;

    private String errorMessage;

    private InforException(String errorMessage) {
        throw new UnsupportedOperationException("Please enter the error code");
    }


}
