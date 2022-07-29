package com.xama.backend.infrastructure.mediatr.spring;

import com.xama.backend.infrastructure.mediatr.core.RequestHandler;
import org.springframework.context.ApplicationContext;

/**
 * A wrapper around a RequestHandler
 **/
public class RequestHandlerProvider<T extends RequestHandler<?, ?>> {
    private ApplicationContext applicationContext;
    private Class<T> clazz;

    public RequestHandlerProvider(ApplicationContext applicationContext, Class<T> clazz) {
        this.applicationContext = applicationContext;
        this.clazz = clazz;
    }

    public T handler() {
        return this.applicationContext.getBean(clazz);
    }
}
