package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonTests {

    @Test
    void shouldReturnNaN() {
        double base = -4.0;

        assertEquals(Double.NaN, Math.sqrt(base));
    }

    @Test
    void shouldBeNull() {
        CharSequence charSequence = null;
        String str = (String) charSequence;

        assertNull(str);
    }

    @Test
    void shouldPrint5() {
        int x = 2;
        for (x = 3; x < 5; x++);

        assertEquals(5, x);
    }

    @Test
    void shouldMultiply() {
        int i1 = 012;
        int i2 = 20;

        // 012 считается числом в восьмеричной системе = 10 в десятичной
        assertEquals(200, i1 * i2);
    }


}
