package com.xama.backend.infrastructure.mediatr.spring;

import com.xama.backend.infrastructure.mediatr.core.CommandHandler;
import org.springframework.context.ApplicationContext;

/**
 * A wrapper around a CommandHandler
 * **/
public class CommandHandlerProvider<T extends CommandHandler<?>> {

    private ApplicationContext applicationContext;
    private Class<T> clazz;

    public CommandHandlerProvider(ApplicationContext applicationContext, Class<T> clazz){
        this.applicationContext = applicationContext;
        this.clazz = clazz;
    }

    public T handler(){
        return this.applicationContext.getBean(clazz);
    }
}
