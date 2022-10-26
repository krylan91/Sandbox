package me.aakrylov.sandbox.tests;

import me.aakrylov.sandbox.annotation.Loggable;
import me.aakrylov.sandbox.aspect.ServiceLogger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ServiceLogger.class})
public class AspectTests {

    @Test
    @Loggable
    void test() {
        System.out.println("I'm loggable!");
    }
}
