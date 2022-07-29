package com.xama.backend.infrastructure.common;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerImpl implements Logger{
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LoggerImpl.class);

    @Autowired
    public LoggerImpl() {
    }

    public void error(String message, Throwable error) {
        logger.error(message, error);
    }

    public void info(String message) {
        logger.info(message);
    }
}
