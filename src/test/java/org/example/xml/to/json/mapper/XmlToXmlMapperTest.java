package org.example.xml.to.json.mapper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlToXmlMapperTest {
    private final XmlToXmlMapper xmlToXmlMapper = new XmlToXmlMapper();

    @ParameterizedTest
    @MethodSource("sourceForXmlTransformationToXml")
    public void xmlToXmlMapper(String xml, String expected) {
        String actual = xmlToXmlMapper.convert(xml);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> sourceForXmlTransformationToXml() {
        return Stream.of(
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
                "<productLines>" +
                    "<productLine>" +
                    "<code>123</code>" +
                    "<description>ProductABC</description>" +
                    "<quantity unit=\"pcs\">50</quantity>" +
                    "<price>10.0</price>" +
                    "<total>500.0</total>" +
                    "</productLine>" +
                    "<productLine>" +
                    "<code>456</code>" +
                    "<description>Product</description>" +
                    "<quantity unit=\"pcs\">100</quantity>" +
                    "<price>15.0</price>" +
                    "<total>200.0</total>" +
                    "</productLine>" +
                    "</productLines>"
            )
        );
    }
}
