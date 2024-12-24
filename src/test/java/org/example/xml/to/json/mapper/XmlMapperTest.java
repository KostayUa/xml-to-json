package org.example.xml.to.json.mapper;

import org.example.xml.to.json.exceptions.UnExpectedListNameNodeException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XmlMapperTest {
    private final XmlMapper xmlMapper = new XmlMapper();

    @Nested
    class Elements {

        @ParameterizedTest
        @MethodSource("sourceForNodeAreEquals")
        public void valueElement(String xml, Node expected) {
            Node actual = xmlMapper.parse(xml);
            assertEquals(expected, actual);
        }

        private static Stream<Arguments> sourceForNodeAreEquals() {
            return Stream.of(
                //NullNode
                Arguments.of("<name></name>", new NullNode("name")),
                Arguments.of("<name>   </name>", new NullNode("name")),
                Arguments.of("<name/>", new NullNode("name")),
                //NullNode with attributes
                Arguments.of(
                    "<name attribute_name=\"attribute_value\"></name>",
                    new NullNode("name", List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE)))
                ),
                //StringNode
                Arguments.of("<name>value1</name>", new StringNode("name", STRING_VALUE1)),
                Arguments.of("<name> value1</name>", new StringNode("name", STRING_VALUE1)),
                Arguments.of("<name>value1 </name>", new StringNode("name", STRING_VALUE1)),
                Arguments.of("<name> value1 </name>", new StringNode("name", STRING_VALUE1)),
                Arguments.of("<name> hello world </name>", new StringNode("name", "hello world")),
                //StringNode with attributes
                Arguments.of(
                    "<name attribute_name=\"attribute_value\">value1</name>",
                    new StringNode("name", STRING_VALUE1,
                        List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE)))
                ),
                //BooleanNode
                Arguments.of("<active>true</active>", new BooleanNode("active", BOOLEAN_VALUE1)),
                Arguments.of("<active> true</active>", new BooleanNode("active", BOOLEAN_VALUE1)),
                Arguments.of("<active>true </active>", new BooleanNode("active", BOOLEAN_VALUE1)),
                Arguments.of("<active> true </active>", new BooleanNode("active", BOOLEAN_VALUE1)),
                Arguments.of("<active>True</active>", new BooleanNode("active", BOOLEAN_VALUE1)),
                Arguments.of("<active>false</active>", new BooleanNode("active", BOOLEAN_VALUE2)),
                Arguments.of("<active> false</active>", new BooleanNode("active", BOOLEAN_VALUE2)),
                Arguments.of("<active>false </active>", new BooleanNode("active", BOOLEAN_VALUE2)),
                Arguments.of("<active> false </active>", new BooleanNode("active", BOOLEAN_VALUE2)),
                Arguments.of("<active>False</active>", new BooleanNode("active", BOOLEAN_VALUE2)),
                //BooleanNode with attributes
                Arguments.of(
                    "<active attribute_name=\"attribute_value\">true</active>",
                    new BooleanNode("active",
                        BOOLEAN_VALUE1,
                        List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE)))
                ),
                Arguments.of(
                    "<active attribute_name=\"attribute_value\">false</active>",
                    new BooleanNode("active",
                        BOOLEAN_VALUE2,
                        List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE)))
                ),
                //NumberNode
                Arguments.of("<id> 1</id>", new NumberNode("id", new BigDecimal("1"))),
                Arguments.of("<id>1 </id>", new NumberNode("id", new BigDecimal("1"))),
                Arguments.of("<id> 1 </id>", new NumberNode("id", new BigDecimal("1"))),
                Arguments.of("<id>0</id>", new NumberNode("id", new BigDecimal("0"))),
                Arguments.of("<id>0.0</id>", new NumberNode("id", new BigDecimal("0.0"))),
                Arguments.of("<id>1</id>", new NumberNode("id", new BigDecimal("1"))),
                Arguments.of("<id> -1</id>", new NumberNode("id", new BigDecimal("-1"))),
                Arguments.of("<id>+1</id>", new NumberNode("id", new BigDecimal("+1"))),
                Arguments.of("<id> -1.02</id>", new NumberNode("id", new BigDecimal("-1.02"))),
                Arguments.of("<id>+0.1</id>", new NumberNode("id", new BigDecimal("0.1"))),
                Arguments.of("<id> -0.1</id>", new NumberNode("id", new BigDecimal("-0.1"))),
                Arguments.of("<id>1.0</id>", new NumberNode("id", new BigDecimal("1.0"))),
                Arguments.of("<id>1.1</id>", new NumberNode("id", new BigDecimal("1.1"))),
                Arguments.of("<id>1.11</id>", new NumberNode("id", new BigDecimal("1.11"))),
                Arguments.of("<id>01.11</id>", new NumberNode("id", new BigDecimal("1.11"))),
                //NumberNode with attributes
                Arguments.of(
                    "<id attribute_name=\"attribute_value\">1</id>",
                    new NumberNode("id", NUMBER1, List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE)))
                ),
                //invalid NumberNode
                Arguments.of("<id>.1</id>", new StringNode("id", ".1")),
                Arguments.of("<id>1.</id>", new StringNode("id", "1.")),
                Arguments.of("<id>1.1.1</id>", new StringNode("id", "1.1.1")),
                Arguments.of("<id>1. 0</id>", new StringNode("id", "1. 0")),
                Arguments.of("<id>1,1</id>", new StringNode("id", "1,1")),
                Arguments.of("<id>123abc</id>", new StringNode("id", "123abc"))
            );
        }
    }

    @Nested
    class ComplexType {

        @Nested
        class WithoutAttributes {

            @Test
            public void objectType1() {
                String xml = """
                      <company>
                           <name>value1</name>
                      </company>
                    """;
                Node expected = new ObjectNode(
                    "company", List.of(new StringNode("name", STRING_VALUE1))
                );
                Node actual = xmlMapper.parse(xml);
                assertEquals(expected, actual);
            }

            @Test
            public void objectType2() {
                String xml = """
                      <company>
                           <name>value1</name>
                           <lastName>value2</lastName>
                      </company>
                    """;
                Node expected = new ObjectNode("company", List.of(
                    new StringNode("name", STRING_VALUE1),
                    new StringNode("lastName", STRING_VALUE2)
                ));
                Node actual = xmlMapper.parse(xml);
                assertEquals(expected, actual);
            }
        }

        @Nested
        class WithAttributes {

            @Test
            public void objectType1() {
                String xml = """
                      <company attribute_name="attribute_value">
                           <name>value1</name>
                      </company>
                    """;
                Node expected = new ObjectNode("company",
                    List.of(new StringNode("name", STRING_VALUE1)),
                    List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))
                );
                Node actual = xmlMapper.parse(xml);
                assertEquals(expected, actual);
            }

            @Test
            public void objectType2() {
                String xml = """
                      <company attribute_name="attribute_value">
                           <name>value1</name>
                           <lastName>value2</lastName>
                      </company>
                    """;
                Node expected = new ObjectNode("company",
                    List.of(
                        new StringNode("name", STRING_VALUE1),
                        new StringNode("lastName", STRING_VALUE2)
                    ),
                    List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))
                );
                Node actual = xmlMapper.parse(xml);
                assertEquals(expected, actual);
            }
        }
    }

    @Nested
    class RepeatableType {

        @Nested
        class InvalidNodeType {

            @Test
            public void UnExpectedNodeType() {
                String xml = """
                      <phones>
                           <phone>value1</phone>
                           <phone>value2</phone>
                           <name>value3</name>
                      </phones>
                    """;
                assertThrows(UnExpectedListNameNodeException.class, () -> xmlMapper.parse(xml));
            }
        }

        @Nested
        class WithoutAttributes {

            @Test
            public void ArrayType() {
                String xml = """
                      <phones>
                           <phone>value1</phone>
                           <phone>value2</phone>
                      </phones>
                    """;
                Node expected = new ArrayNode("phones",
                    List.of(
                        new StringNode("phone", STRING_VALUE1),
                        new StringNode("phone", STRING_VALUE2)
                    )
                );
                Node actual = xmlMapper.parse(xml);
                assertEquals(expected, actual);
            }
        }

        @Nested
        class WithAttributes {

            @Test
            public void ArrayElement() {
                String xml = """
                      <phones attribute_name="attribute_value">
                           <phone>value1</phone>
                           <phone>value2</phone>
                      </phones>
                    """;
                Node expected = new ArrayNode("phones",
                    List.of(
                        new StringNode("phone", STRING_VALUE1),
                        new StringNode("phone", STRING_VALUE2)
                    ),
                    List.of(new Attribute(ATTRIBUTE_NAME, ATTRIBUTE_VALUE))
                );
                Node actual = xmlMapper.parse(xml);
                assertEquals(expected, actual);
            }
        }
    }

    private static final String ATTRIBUTE_NAME = "attribute_name";
    private static final String ATTRIBUTE_VALUE = "attribute_value";
    private static final String STRING_VALUE1 = "value1";
    private static final String STRING_VALUE2 = "value2";
    private static final BigDecimal NUMBER1 = new BigDecimal(1);
    private static final boolean BOOLEAN_VALUE1 = true;
    private static final boolean BOOLEAN_VALUE2 = false;
}
