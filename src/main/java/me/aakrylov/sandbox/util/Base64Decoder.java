package me.aakrylov.sandbox.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Base64Decoder {

    private final Base64 base64 = new Base64();

    public String decodeSingle(String stringToDecode) {
        return new String(base64.decode(stringToDecode.getBytes(StandardCharsets.UTF_8)));
    }

    public Map<String, String> decodeMultiple(Map<String, String> encodedStringsByName) {
        return encodedStringsByName.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new String(base64.decode(entry.getValue().getBytes(StandardCharsets.UTF_8)))));
    }

}
