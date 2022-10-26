package me.aakrylov.sandbox.components;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class RetryComponent {

    private static final Logger logger = LoggerFactory.getLogger(RetryComponent.class);
    private static final long INTEGER_BASE = 1000;

    public static void withRetry(int times, int timeout, Runnable action, Runnable errorCallback) {
        retry(times, timeout, action);
        errorCallback.run();
    }

    @SneakyThrows
    public static void withRetry(int times, int timeout, Runnable action) {
        retry(times, timeout, action);
    }

    private static void retry(int times, int timeout, Runnable action) {
        Exception lastException = new Exception("Ошибка выполнения");
        for (int i = 0; i < times; i++) {
            try {
                action.run();
            } catch (Exception e) {
                String errorMessage = Objects.nonNull(e.getCause()) ? e.getCause().getMessage() : e.getMessage();
                logger.warn("Ошибка выполнения, попытка {}/{}: {}", i + 1, times, errorMessage);
                lastException = e;
                sleep(timeout * INTEGER_BASE);
            }
        }
        logger.error("Попытки исчерпаны. Последняя ошибка: {}", lastException.getMessage());
    }

    @SneakyThrows
    private static void sleep(long millis) {
        Thread.sleep(millis);
    }
}
