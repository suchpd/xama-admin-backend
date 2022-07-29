package com.xama.backend.infrastructure.mediatr.core.exception;

public class NoRequestHandlerException extends RuntimeException {
    public NoRequestHandlerException(String message) {
        super(message);
    }
}
