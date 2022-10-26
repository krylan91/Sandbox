package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanTests {

    @Test
    void shouldReturnTrue() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime beginDate = LocalDateTime.now().minusDays(10);
        LocalDateTime endDate = LocalDateTime.now().plusDays(10);

        assertTrue(beginDate.isBefore(now) && endDate.isAfter(now) || Objects.isNull(endDate));
    }
}
