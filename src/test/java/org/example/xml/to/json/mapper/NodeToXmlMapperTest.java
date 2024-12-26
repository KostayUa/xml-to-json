package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeToXmlMapperTest {
    private final NodeToXmlMapper nodeMapper = new NodeToXmlMapper();

    @Nested
    class Elements {

        @ParameterizedTest
        @MethodSource("sourceNodeForFullFormat")
        public void valueElement1(Node node, String expected) {
            String actual = nodeMapper.convert(node, NodeToXmlMapper.NullTagFormat.FULL);
            assertEquals(expected, actual);
        }

        @ParameterizedTest
        @MethodSource("sourceNodeForShortFormat")
        public void valueElement(Node node, String expected) {
            String actual = nodeMapper.convert(node, NodeToXmlMapper.NullTagFormat.SHORT);
            assertEquals(expected, actual);
        }

        private static Stream<Arguments> sourceNodeForFullFormat() {
            Stream<Arguments> nullNodesTestData = Stream.of(
                //NullNode
                Arguments.of(new NullNode("name"), "<name></name>"),
                //NullNode with attributes
                Arguments.of(
                    new NullNode("name", List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))),
                    "<name attribute_name=\"attribute_value\"></name>"
                )
            );
            return Stream.concat(nullNodesTestData, GENERAL_TEST_DATA.stream());
        }

        private static Stream<Arguments> sourceNodeForShortFormat() {
            Stream<Arguments> nullNodesTestData = Stream.of(
                //NullNode
                Arguments.of(new NullNode("name"), "<name/>"),
                //NullNode with attributes
                Arguments.of(
                    new NullNode(
                        "name",
                        List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))
                    ),
                    "<name attribute_name=\"attribute_value\"/>"
                )
            );
            return Stream.concat(nullNodesTestData, GENERAL_TEST_DATA.stream());
        }
    }

    @Nested
    class ComplexType {

        @Nested
        class WithoutAttributes {

            @Test
            public void objectType() {
                Node node = new ObjectNode("company", List.of(new StringNode("name", STRING_VALUE1)));
                String expected = "<company><name>value1</name></company>";
                String actual = nodeMapper.convert(node, NodeToXmlMapper.NullTagFormat.FULL);
                assertEquals(expected, actual);
            }
        }

        @Nested
        class WithAttribute {

            @Test
            public void objectTypeWithAttribute() {
                Node node = new ObjectNode(
                    "company",
                    List.of(new StringNode("name", STRING_VALUE1)),
                    List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))
                );
                String expected = "<company attribute_name=\"attribute_value\"><name>value1</name></company>";
                String actual = nodeMapper.convert(node, NodeToXmlMapper.NullTagFormat.FULL);
                assertEquals(expected, actual);
            }
        }
    }

    @Nested
    class RepeatableType {

        @Nested
        class WithoutAttributes {

            @Test
            public void ArrayType() {
                Node node = new ArrayNode(
                    "phones",
                    List.of(
                        new StringNode("phone", STRING_VALUE1),
                        new StringNode("phone", STRING_VALUE2)
                    )
                );
                String expected = "<phones><phone>value1</phone><phone>value2</phone></phones>";
                String actual = nodeMapper.convert(node, NodeToXmlMapper.NullTagFormat.FULL);
                assertEquals(expected, actual);
            }
        }

        @Nested
        class WithAttributes {

            @Test
            public void ArrayTypeWithAttributes() {
                Node node = new ArrayNode(
                    "phones",
                    List.of(
                        new StringNode("phone", STRING_VALUE1),
                        new StringNode("phone", STRING_VALUE2)
                    ),
                    List.of(
                        new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE)
                    )
                );

                String expected = "<phones attribute_name=\"attribute_value\">" +
                    "<phone>value1</phone>" +
                    "<phone>value2</phone>" +
                    "</phones>";
                String actual = nodeMapper.convert(node, NodeToXmlMapper.NullTagFormat.FULL);
                assertEquals(expected, actual);
            }
        }
    }

    private static final String ATTRIBUTE_NAME = "attribute_name";
    private static final String ATTRIBUTE_VALUE = "attribute_value";
    private static final String STRING_VALUE1 = "value1";
    private static final String STRING_VALUE2 = "value2";

    private static final List<Arguments> GENERAL_TEST_DATA = List.of(
        //StringNode
        Arguments.of(new StringNode("name", "value"), "<name>value</name>"),
        Arguments.of(new StringNode("name", "hello world"), "<name>hello world</name>"),
        //StringNode with attributes
        Arguments.of(
            new StringNode(
                "name",
                STRING_VALUE1,
                List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))
            ),
            "<name attribute_name=\"attribute_value\">value1</name>"
        ),
        //BooleanNode
        Arguments.of(new BooleanNode("active", true), "<active>true</active>"),
        Arguments.of(new BooleanNode("active", false), "<active>false</active>"),
        //BooleanNode with attributes
        Arguments.of(
            new BooleanNode(
                "active",
                true,
                List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))
            ),
            "<active attribute_name=\"attribute_value\">true</active>"
        ),
        //NumberNode
        Arguments.of(new NumberNode("id", new BigDecimal("1")), "<id>1</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("0")), "<id>0</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("0.0")), "<id>0.0</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("-1")), "<id>-1</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("+1")), "<id>1</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("-1.02")), "<id>-1.02</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("+1.1")), "<id>1.1</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("-1.1")), "<id>-1.1</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("1.0")), "<id>1.0</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("1.1")), "<id>1.1</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("1.11")), "<id>1.11</id>"),
        Arguments.of(new NumberNode("id", new BigDecimal("01.11")), "<id>1.11</id>"),
        //NumberNode with attribute
        Arguments.of(
            new NumberNode(
                "id",
                new BigDecimal("1"),
                List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))
            ),
            "<id attribute_name=\"attribute_value\">1</id>"
        )
    );
}
