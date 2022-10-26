package me.aakrylov.sandbox.tests;

import me.aakrylov.sandbox.components.CustomPair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomPairTests {

    private final CustomPair<String, String> firstPair = CustomPair.of(new String("123"), new String("456"));
    private final CustomPair<String, String> secondPair = CustomPair.of(new String("123"), new String("456"));

    @Test
    void shouldReturnTrue() {
        assertEquals(firstPair, secondPair);
    }

    @Test
    void shouldFindElementInList() {
        CustomPair<String, String> searchablePair = CustomPair.of(new String("123"), new String("456"));
        List<CustomPair<String, String>> pairs = Stream.of(firstPair, secondPair).toList();

        assertTrue(pairs.contains(searchablePair));
    }
}
