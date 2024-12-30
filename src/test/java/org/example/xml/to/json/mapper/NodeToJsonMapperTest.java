package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeToJsonMapperTest {
    private final NodeToJsonMapper nodeToJsonMapper = new NodeToJsonMapper();

    @Nested
    class Element {

        @ParameterizedTest
        @MethodSource("sourceNodeForValueJson")
        public void valueElement(Node node, String expected) {
            String actual = nodeToJsonMapper.convert(node);
            assertEquals(expected, actual);
        }

        private static Stream<Arguments> sourceNodeForValueJson() {
            return Stream.of(
                //NullNode
                Arguments.of(new NullNode("name"), "{\"name\":null}"),
                //StringNode
                Arguments.of(new StringNode("name", "value"), "{\"name\":\"value\"}"),
                Arguments.of(new StringNode("name", ""), "{\"name\":\"\"}"),
                //NumberNode
                Arguments.of(new NumberNode("id", new BigDecimal("1")), "{\"id\":1}"),
                Arguments.of(new NumberNode("id", new BigDecimal("0")), "{\"id\":0}"),
                Arguments.of(new NumberNode("id", new BigDecimal("0.0")), "{\"id\":0.0}"),
                Arguments.of(new NumberNode("id", new BigDecimal("-1")), "{\"id\":-1}"),
                Arguments.of(new NumberNode("id", new BigDecimal("+1")), "{\"id\":1}"),
                Arguments.of(new NumberNode("id", new BigDecimal("-1.02")), "{\"id\":-1.02}"),
                Arguments.of(new NumberNode("id", new BigDecimal("+1.1")), "{\"id\":1.1}"),
                Arguments.of(new NumberNode("id", new BigDecimal("-1.1")), "{\"id\":-1.1}"),
                Arguments.of(new NumberNode("id", new BigDecimal("1.0")), "{\"id\":1.0}"),
                Arguments.of(new NumberNode("id", new BigDecimal("1.1")), "{\"id\":1.1}"),
                Arguments.of(new NumberNode("id", new BigDecimal("1.11")), "{\"id\":1.11}"),
                Arguments.of(new NumberNode("id", new BigDecimal("01.11")), "{\"id\":1.11}"),
                //BooleanNode
                Arguments.of(new BooleanNode("active", true), "{\"active\":true}"),
                Arguments.of(new BooleanNode("active", false), "{\"active\":false}"),
                //ObjectNode
                //TODO: Arguments.of(new ObjectNode("name", List.of()), "{\"name\":{}}"),
//                Arguments.of(new ObjectNode("name", List.of()), "{\"name\":{}}"),
                Arguments.of(
                    new ObjectNode("company", List.of(new StringNode("name", "value"))),
                    "{\"company\":{\"name\":\"value\"}}"
                ),
                Arguments.of(
                    new ObjectNode(
                        "company",
                        List.of(
                            new StringNode("name", "value1"),
                            new StringNode("lastName", "value2")
                        )
                    ),
                    "{\"company\":{\"name\":\"value1\",\"lastName\":\"value2\"}}"
                ),
                //ArrayNode
                //TODO: Arguments.of(new ArrayNode("phones", List.of()), "{\"phones\":[]}"),
//                Arguments.of(new ArrayNode("phones", List.of()), "{\"phones\":[]}"),
                Arguments.of(
                    new ArrayNode("phones", List.of(new StringNode("phone", "number"))),
                    "{\"phones\":[{\"phone\":\"number\"}]}"
                ),
                Arguments.of(
                    new ArrayNode(
                        "phones",
                        List.of(
                            new StringNode("phone", "number1"),
                            new StringNode("mobile", "number2")
                        )
                    ),
                    "{\"phones\":[{\"phone\":\"number1\",\"mobile\":\"number2\"}]}"
                )
            );
        }
    }

//    @Nested
//    class ObjectType {
//
//        @Test
//        public void test1() {
//            Node node = new ObjectNode("company", List.of(new StringNode("name", "value")));
//            String expected = "{\"company\":{\"name\":\"value\"}}";
//            String actual = nodeToJsonMapper.convert(node);
//            assertEquals(expected, actual);
//        }
//
//        @Test
//        public void test2() {
//            Node node = new ObjectNode(
//                "company",
//                List.of(
//                    new StringNode("name", "value1"),
//                    new StringNode("lastName", "value2")
//                )
//            );
//            String expected = "{\"company\":{\"name\":\"value1\",\"lastName\":\"value2\"}}";
//            String actual = nodeToJsonMapper.convert(node);
//            assertEquals(expected, actual);
//        }
//    }

//    @Nested
//    class ArrayType {
//
//        @Test
//        public void test1() {
//            Node node = new ArrayNode("phones", List.of(new StringNode("phone", "number")));
//            String expected = "{\"phones\":[{\"phone\":\"number\"}]}";
//            String actual = nodeToJsonMapper.convert(node);
//            assertEquals(expected, actual);
//        }
//
//        @Test
//        public void test2() {
//            Node node = new ArrayNode(
//                "phones",
//                List.of(
//                    new StringNode("phone", "number1"),
//                    new StringNode("phone", "number2")
//                )
//            );
//            String expected = "{\"phones\":[{\"phone\":\"number1\"},{\"phone\":\"number2\"}]}";
//            String actual = nodeToJsonMapper.convert(node);
//            assertEquals(expected, actual);
//        }
//    }
}
