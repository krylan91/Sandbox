package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CastingTests {

    private Object longValue = Long.MAX_VALUE;
    private Integer intValue;

    @Test
    void shouldThrowClassCastException() {
        assertThrows(ClassCastException.class, () -> {
            intValue = (Integer) longValue;
        });
    }

    @Test
    void shouldCastLongToInt() {
        intValue = Math.toIntExact((Long) longValue);
        assertEquals(intValue.intValue(), (((Long) longValue).intValue()));
    }

    @Test
    void test() {
        intValue = ((Long) longValue).intValue();
        System.out.println(intValue);
    }
}
