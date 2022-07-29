package com.xama.backend.infrastructure.mediatr.core;

/**
 * A handler for give command
 * @param <C>
 */
public interface CommandHandler <C extends Command>{

    void handle(C t);
}
