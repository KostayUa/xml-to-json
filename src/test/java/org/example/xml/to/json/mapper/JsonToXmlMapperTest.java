package org.example.xml.to.json.mapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonToXmlMapperTest {
    private final JsonToXmlMapper jsonToXmlMapper = new JsonToXmlMapper();

    @ParameterizedTest
    @MethodSource("sourceForJsonTransformationToXml")
    public void jsonToXmlMapper(String xml, String expected) {
        String actual = jsonToXmlMapper.convert(xml);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> sourceForJsonTransformationToXml() {
        return Stream.of(
            Arguments.of(
                "{" +
                    "\"productLines\": [" +
                    "{" +
                    "\"productLine\": {" +
                    "\"code\": 123, " +
                    "\"description\": \"ProductABC\", " +
                    "\"price\": 10.0, " +
                    "\"total\": 500.0" +
                    "}" +
                    "}, " +
                    "{" +
                    "\"productLine\": {" +
                    "\"code\": 456, " +
                    "\"description\": \"Product\", " +
                    "\"price\": 15.0, " +
                    "\"total\": 200.0" +
                    "}" +
                    "}" +
                    "]" +
                    "}",
                "<productLines>" +
                    "<productLine>" +
                    "<code>123</code>" +
                    "<description>ProductABC</description>" +
                    "<price>10.0</price>" +
                    "<total>500.0</total>" +
                    "</productLine>" +
                    "<productLine>" +
                    "<code>456</code>" +
                    "<description>Product</description>" +
                    "<price>15.0</price>" +
                    "<total>200.0</total>" +
                    "</productLine><" +
                    "/productLines>"
            )
        );
    }
}
