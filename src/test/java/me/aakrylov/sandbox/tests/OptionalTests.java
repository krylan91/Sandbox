package me.aakrylov.sandbox.tests;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionalTests {

    @Test
    void shouldReturnEmptyResponse() {
        String nullString = null;
        ResponseEntity<Integer> stringLengthResponse = Optional.ofNullable(nullString)
                .map(String::length)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        assertEquals(HttpStatus.NOT_FOUND, stringLengthResponse.getStatusCode());
    }

    @Test
    void shouldReturnStringLengthResponse() {
        String nullString = "This is sample string";
        ResponseEntity<Integer> stringLengthResponse = Optional.ofNullable(nullString)
                .map(String::length)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

        assertEquals(HttpStatus.OK, stringLengthResponse.getStatusCode());
        assertEquals(21, stringLengthResponse.getBody());
    }

}
