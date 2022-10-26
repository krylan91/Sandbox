package me.aakrylov.sandbox.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MapperTests {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class TestObject {
        private String STRINGFIRST;
        private String stringSecond;
    }

    @Test
    @SneakyThrows
    void shouldNotMap() {
        String jsonString = """
                {
                  "STRINGFIRST": "This is first string",
                  "stringSecond": "This is second string"
                }""";
        assertThrows(UnrecognizedPropertyException.class, () -> mapper.readValue(jsonString, TestObject.class));
    }

    @Test
    @SneakyThrows
    void shouldMap() {
        String jsonString = """
                {
                  "STRINGFIRST": "This is first string",
                  "STRINGSECOND": "This is second string"
                }""";
        PropertyNamingStrategy pns = new PropertyNamingStrategy.PropertyNamingStrategyBase() {
            @Override
            public String translate(String propertyName) {
                return propertyName.toUpperCase();
            }

        };
        mapper.setPropertyNamingStrategy(pns);
        TestObject testObject = mapper.readValue(jsonString, TestObject.class);

        assertEquals("This is first string", testObject.getSTRINGFIRST());
        assertEquals("This is second string", testObject.getStringSecond());
    }



}
