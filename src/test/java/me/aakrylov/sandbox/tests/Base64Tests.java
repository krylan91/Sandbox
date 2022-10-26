package me.aakrylov.sandbox.tests;

import me.aakrylov.sandbox.util.Base64Decoder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

class Base64Tests {

    private static final Logger logger = LoggerFactory.getLogger(Base64Tests.class);

    @Test
    void shouldDecodeBase64String() {
        Map<String, String> encodedData = Map.of(
                "dbUrl", "amRiYzpwb3N0Z3Jlc3FsOi8vcG9zdGdyZXMtcmVsZWFzZS5lcG0tc3R0cy1kZXYuc3ZjLmNsdXN0ZXIubG9jYWw6NTQzMi9wb3N0Z3Jlcw==",
                "username", "cG9zdGdyZXM=",
                "dbName", "cG9zdGdyZXM=",
                "password", "MTIzNDU2"
        );
        Base64Decoder decoder = new Base64Decoder();
        Map<String, String> decodedData = decoder.decodeMultiple(encodedData);
        decodedData.forEach((key, value) -> logger.info("Parameter={}, value={}", key, value));

        assertFalse(decodedData.isEmpty());
    }
}
