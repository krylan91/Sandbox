package me.aakrylov.sandbox.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class CollectionsTests {

    class Point {
        private final int i;

        public Point(int i) {
            this.i = i;
        }

        @Override
        public int hashCode() {
            return 2;
        }
    }

    enum Counter {
        UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE
    }

    @Test
    void shouldAddTwoElements() {
        Point point1 = new Point(1);
        Point point2 = new Point(1);
        Set<Point> set = new HashSet<>();
        set.add(point1);
        set.add(point2);

        log.info("Because we have not overriden equals() method, " +
                "objects are compared by link (==), " +
                "and thus they are different objects, and thus they are both added to set.");

        assertEquals(2, set.size());
    }

    @Test
    void shouldLeaveFourElements() {
        EnumSet<Counter> countSet1 = EnumSet.range(Counter.TRES, Counter.CINCO); // 1
        EnumSet<Counter> countSet2 = EnumSet.complementOf(countSet1 ); // 2
        String expectedString = "UNO,DOS,SEIS,SIETE";
        String actualString = countSet2.stream().map(Enum::toString).collect(Collectors.joining(","));

        assertEquals(4, countSet2.size());
        assertEquals(expectedString, actualString);
    }

    @Test
    void shouldThrowStackOverflowError() {
        Set<Object> set = new LinkedHashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        set.add(set);

        assertThrows(StackOverflowError.class, () -> System.out.println(set.hashCode()));
    }

    @Test
    void queueTest() {
        Queue<String> queue = new LinkedList<>();

    }
}
