package me.aakrylov.sandbox.tests;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static me.aakrylov.sandbox.components.RetryComponent.withRetry;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class RetryTests {

    @Data
    static class RetryObject {
        private int failureCount = 0;
        private int counter = 0;
        private String message = "Attempt count:";
        public void increaseFailures() {
            this.failureCount++;
        }
        public void increaseCounter() {
            this.counter++;
        }
    }

    @Test
    void shouldNotRetry() {
        RetryObject retryObject = new RetryObject();
        while (canRetry(retryObject)) {
            try {
                doSomething(retryObject);
                break;
            } catch (Exception e) {
                doOnFailure(retryObject);
            }
        }

        assertEquals(0, retryObject.getFailureCount());
        assertEquals(1, retryObject.getCounter());
    }

    @Test
    void shouldRetry() {
        RetryObject retryObject = new RetryObject();
        while (canRetry(retryObject)) {
            try {
                doAndThrow(retryObject);
                break;
            } catch (Exception e) {
                doOnFailure(retryObject);
            }
        }

        assertEquals(10, retryObject.getFailureCount());
        assertEquals(0, retryObject.getCounter());
    }

    @Test
    void shouldRepeatFiveTimes_andExecuteCallbackFunction() {
        int times = 5;
        int timeout = 1;
        withRetry(times, timeout, () -> printLine(), () -> printErrorLine());
    }

    @Test
    void shouldRepeatFiveTimes() {
        int times = 5;
        int timeout = 1;
        withRetry(times, timeout, () -> printLine());
    }

    @Test
    void shouldRepeatFiveTimes_andDoSomeLogic() {
        withRetry(5, 1, () -> {
            String str = getString();
            System.out.println(str);

        });
    }

    private String getString() {
        return "String";
    }

    private String printLine() {
        System.out.println("Start process!");
        throw new RuntimeException("Runtime exception");
    }

    public void localVariableMultithreading() {
        Executor executor = (runnable) -> new Thread(runnable).start();
        final boolean[] run = {true};
        executor.execute(() -> {
            while (run[0]) {
                // do operation
            }
        });

        run[0] = false;
    }

    private void printErrorLine() {
        System.err.println("Error callback");
    }

    private boolean canRetry(RetryObject retryObject) {
        log.info("Failure count: {}", retryObject.getFailureCount());
        return retryObject.getFailureCount() < 10;
    }

    private void doSomething(RetryObject retryObject) {
        log.info("Do something. Counter: {}", retryObject.getCounter());
        String message = retryObject.getMessage();
        retryObject.increaseCounter();
        message = String.join(",", message, String.valueOf(retryObject.getCounter()));
        retryObject.setMessage(message);
    }

    private void doOnFailure(RetryObject retryObject) {
        log.error("Failure count: {}", retryObject.getFailureCount());
        retryObject.increaseFailures();
    }

    private void doAndThrow(RetryObject retryObject) {
        log.info("Throw exception");
        throw new RuntimeException("Exception while doing something.");
    }
}
