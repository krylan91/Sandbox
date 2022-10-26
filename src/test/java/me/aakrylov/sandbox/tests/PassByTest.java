package me.aakrylov.sandbox.tests;

import me.aakrylov.sandbox.model.Balloon;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PassByTest {

    private static final Logger logger = LoggerFactory.getLogger(PassByTest.class);

    private Balloon red = new Balloon("Red");
    private Balloon blue = new Balloon("Blue");

    @Test
    void shouldNotSwapBaloons() {
        swap(red, blue);
        assertEquals("Red", red.getColor());
        assertEquals("Blue", blue.getColor());
    }

    @Test
    void shouldChangeColor() {
        changeColor(blue);
        assertEquals("Green", blue.getColor());
    }

    @Test
    void shouldNotChange() {
        List<String> list = Stream.of("String1", "String2").collect(Collectors.toList());
        logger.info("Before {}", list);
        processList_1(list);
        logger.info("After {}", list);

        assertEquals(2, list.size());
    }

    @Test
    void shouldChange() {
        List<String> list = Stream.of("String1", "String2").collect(Collectors.toList());
        logger.info("Before {}", list);
        processList_2(list);
        logger.info("After {}", list);

        assertEquals(3, list.size());
    }

    private void swap(Object o1, Object o2) {
        Object temp = o1;
        o1 = o2;
        o2 = temp;
    }

    private void changeColor(Balloon balloon) {
        balloon.setColor("Green");
        balloon = new Balloon("Yellow");
        balloon.setColor("Amaranth");
    }

    private void processList_1(List<String> list) {
        list = List.of("String3");
    }

    private void processList_2(List<String> list) {
        list.add("String3");
    }
}
