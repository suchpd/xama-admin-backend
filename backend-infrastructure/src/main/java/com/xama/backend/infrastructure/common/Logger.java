package com.xama.backend.infrastructure.common;

public interface Logger {
    void error(String message, Throwable error);

    void info(String message);
}
