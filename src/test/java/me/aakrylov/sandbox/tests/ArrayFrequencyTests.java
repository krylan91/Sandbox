package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

public class ArrayFrequencyTests {

    private static final Logger logger = LoggerFactory.getLogger(ArrayFrequencyTests.class);

    @Test
    void countFrequency() {
        int[] arr = new int[]{1, 2, 8, 3, 2, 2, 2, 5, 1};
        Map<Integer, Integer> frequencies = new TreeMap<>();

        for (int i: arr) {
            frequencies.put(i, count(arr, i));
        }
        frequencies.forEach((key, value) -> logger.info("Frequency of {} is {}", key, value));
    }

    private int count(int[] array, Integer i) {
        int result = 0;
        for (Object in : array)
            if (i.equals(in))
                result++;

        return result;
    }
}
