package me.aakrylov.sandbox.tests;

import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerTests {

    @Data
    static class IntBox {
        private Integer intBoxed;
    }

    @Test
    void shouldThrowException() {
        IntBox intBox = new IntBox();
        assertThrows(NullPointerException.class, () -> {
            int intUnboxed = intBox.getIntBoxed();
        });
    }
}
