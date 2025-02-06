package org.example.xml.to.json.mapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlToJsonMapperTest {
    private final XmlToJsonMapper xmlToJsonMapper = new XmlToJsonMapper();

    @ParameterizedTest
    @MethodSource("sourceForXmlTransformationToJson")
    public void xmlToJsonMapper(String xml, String expected) {
        String actual = xmlToJsonMapper.convert(xml);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> sourceForXmlTransformationToJson() {
        return Stream.of(
            Arguments.of("""
                      <company>
                        <name>Delivery of future</name>
                        <address>Soborna</address>
                        <cityStZip>Ir41400</cityStZip>
                        <phone>(093) 439-0011</phone>
                        <fax>(050) 332-8734</fax>
                        <website>https://delivery.com</website>
                      </company>
                    """,
                "{" +
                    "\"company\": {" +
                    "\"name\": \"Delivery of future\", " +
                    "\"address\": \"Soborna\", " +
                    "\"cityStZip\": \"Ir41400\", " +
                    "\"phone\": \"(093) 439-0011\", " +
                    "\"fax\": \"(050) 332-8734\", " +
                    "\"website\": \"https://delivery.com\"" +
                    "}" +
                    "}"
            ),
            Arguments.of(
                """
                      <productLines>
                        <productLine>
                          <code>123</code>
                          <description>ProductABC</description>
                          <quantity unit="pcs">50</quantity>
                          <price>10.0</price>
                          <total>500.0</total>
                        </productLine>
                        <productLine>
                          <code>456</code>
                          <description>Product</description>
                          <quantity unit="pcs">100</quantity>
                          <price>15.0</price>
                          <total>200.0</total>
                        </productLine>
                      </productLines>
                    """,
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
                    "\"total\": 500.0}}, " +
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
