package me.aakrylov.sandbox.tests;

import me.aakrylov.sandbox.components.CustomPair;
import org.junit.jupiter.api.Test;

public class ReflectionTests {

    @Test
    void shouldDisplayExistingFieldValue() {
        CustomPair<String, String> pair = CustomPair.of("Hello, ", null);
    }
}
