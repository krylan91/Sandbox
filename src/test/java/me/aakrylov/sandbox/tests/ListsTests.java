package me.aakrylov.sandbox.tests;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListsTests {

    @Test
    void shouldLeaveNotPresentElements() {
        List<String> firstList = List.of("This", "is", "first", "string");
        List<String> secondList = List.of("This", "is", "second", "record");

        List<String> finalList = firstList.stream()
                .filter(el -> !secondList.contains(el)).toList();

        assertEquals(2, finalList.size());
        assertEquals("first", finalList.get(0));
        assertEquals("string", finalList.get(1));
    }

    @Test
    void shouldFilterExistingList() {
        List<String> firstList = List.of("This", "is", "first", "string");
        firstList = firstList.stream()
                .filter(el -> el.equals("is"))
                .collect(Collectors.toList());

        assertEquals(1, firstList.size());
        assertEquals("is", firstList.get(0));
    }

    @Test
    void shouldContainPair() {
        List<Pair<String, String>> initialList = List.of(
                Pair.of("1", "1"),
                Pair.of("2", "2"),
                Pair.of("3", "3")
        );

        Pair<String, String> filter = Pair.of("1", "1");

        boolean contains = initialList.contains(filter);

        assertTrue(contains);
    }
}
