package org.example.xml.to.json.mapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonToJsonMapperTest {
    private final JsonToJsonMapper jsonToJsonMapper = new JsonToJsonMapper();

    @ParameterizedTest
    @MethodSource("sourceForJsonTransformationToJson")
    public void jsonToJsonMapper(String xml, String expected) {
        String actual = jsonToJsonMapper.convert(xml);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> sourceForJsonTransformationToJson() {
        return Stream.of(
            Arguments.of(
                "{" +
                    "\"productLines\": [" +
                    "{" +
                    "\"productLine\": {" +
                    "\"code\": 123, " +
                    "\"description\": \"ProductABC\", " +
                    "\"quantity\": 50, " +
                    "\"quantity_attributes\": {" +
                    "\"unit\": \"pcs\"" +
                    "}, " +
                    "\"price\": 10.0, " +
                    "\"total\": 500.0" +
                    "}" +
                    "}, " +
                    "{" +
                    "\"productLine\": {" +
                    "\"code\": 456, " +
                    "\"description\": \"Product\", " +
                    "\"quantity\": 100, " +
                    "\"quantity_attributes\": {" +
                    "\"unit\": \"pcs\"" +
                    "}, " +
                    "\"price\": 15.0, " +
                    "\"total\": 200.0" +
                    "}" +
                    "}" +
                    "]" +
                    "}",
                "{" +
                    "\"productLines\": [" +
                    "{" +
                    "\"productLine\": {" +
                    "\"code\": 123, " +
                    "\"description\": \"ProductABC\", " +
                    "\"quantity\": 50, " +
                    "\"quantity_attributes\": {" +
                    "\"unit\": \"pcs\"" +
                    "}, " +
                    "\"price\": 10.0, " +
                    "\"total\": 500.0" +
                    "}" +
                    "}, " +
                    "{" +
                    "\"productLine\": {" +
                    "\"code\": 456, " +
                    "\"description\": \"Product\", " +
                    "\"quantity\": 100, " +
                    "\"quantity_attributes\": {" +
                    "\"unit\": \"pcs\"" +
                    "}, " +
                    "\"price\": 15.0, " +
                    "\"total\": 200.0" +
                    "}" +
                    "}" +
                    "]" +
                    "}"
            )
        );
    }
}
