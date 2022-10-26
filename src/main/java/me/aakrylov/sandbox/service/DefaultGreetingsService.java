package me.aakrylov.sandbox.service;

import me.aakrylov.sandbox.annotation.Loggable;
import me.aakrylov.sandbox.service.api.GreetingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DefaultGreetingsService implements GreetingsService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultGreetingsService.class);

    @Override
    @Loggable
    public String defaultGreeting() {
        return "Hello, world!";
    }

    @Override
    @Loggable
    public String namedGreeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return "Hello, " + name + "!";
    }

    @Override
    public String delayedGreeting() {
        try {
            Thread.sleep(30 * 1000);
            return "Hello, man!";
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted!", e);
            Thread.currentThread().interrupt();
        }
        return "Something gone wrong";
    }
}
