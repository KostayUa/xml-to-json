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
                //Null
                Arguments.of(new NullNode(), "null"),
                Arguments.of(new NullNode("id"), "{\"id\": null}"),
                Arguments.of(
                    new NullNode(
                        "id",
                        List.of(
                            new Attribute("attribute_name", "attribute_value")
                        )
                    ),
                    "{\"id\": null, \"id_attributes\": {\"attribute_name\": \"attribute_value\"}}"
                ),
                Arguments.of(
                    new NullNode(
                        "id",
                        List.of(
                            new Attribute("attribute_name", "attribute_value"),
                            new Attribute("attribute_name2", "attribute_value2")
                        )
                    ),
                    "{\"id\": null, \"id_attributes\": " +
                        "{\"attribute_name\": \"attribute_value\", \"attribute_name2\": \"attribute_value2\"}}"
                ),
                //Boolean
                Arguments.of(new BooleanNode(true), "true"),
                Arguments.of(new BooleanNode(false), "false"),
                Arguments.of(new BooleanNode("isActive", true), "{\"isActive\": true}"),
                Arguments.of(
                    new BooleanNode(
                        "isActive",
                        true,
                        List.of(new Attribute("attribute_name", "attribute_value"))
                    ),
                    "{\"isActive\": true, \"isActive_attributes\": {\"attribute_name\": \"attribute_value\"}}"
                ),
                Arguments.of(
                    new BooleanNode(
                        "isActive",
                        true,
                        List.of(
                            new Attribute("attribute_name", "attribute_value"),
                            new Attribute("attribute_name2", "attribute_value2")
                        )
                    ),
                    "{\"isActive\": true, \"isActive_attributes\": " +
                        "{\"attribute_name\": \"attribute_value\", \"attribute_name2\": \"attribute_value2\"}}"
                ),
                //String
                Arguments.of(new StringNode(""), "\"\""),
                Arguments.of(new StringNode("   "), "\"   \""),
                Arguments.of(new StringNode("text"), "\"text\""),
                Arguments.of(new StringNode("name", "text"), "{\"name\": \"text\"}"),
                Arguments.of(
                    new StringNode(
                        "name",
                        "text",
                        List.of(new Attribute("attribute_name", "attribute_value"))
                    ),
                    "{\"name\": \"text\", \"name_attributes\": {\"attribute_name\": \"attribute_value\"}}"
                ),
                Arguments.of(
                    new StringNode(
                        "name",
                        "text",
                        List.of(
                            new Attribute("attribute_name", "attribute_value"),
                            new Attribute("attribute_name2", "attribute_value2")
                        )
                    ),
                    "{\"name\": \"text\", \"name_attributes\": " +
                        "{\"attribute_name\": \"attribute_value\", \"attribute_name2\": \"attribute_value2\"}}"
                ),
                //Number
                Arguments.of(new NumberNode(new BigDecimal("-123")), "-123"),
                Arguments.of(new NumberNode(new BigDecimal("123")), "123"),
                Arguments.of(new NumberNode(new BigDecimal("-1.0")), "-1.0"),
                Arguments.of(new NumberNode(new BigDecimal("1.0")), "1.0"),
                Arguments.of(new NumberNode("phone", new BigDecimal("1.0")), "{\"phone\": 1.0}"),
                Arguments.of(
                    new NumberNode(
                        "phone",
                        new BigDecimal("1.0"),
                        List.of(new Attribute("attribute_name", "attribute_value"))
                    ),
                    "{\"phone\": 1.0, \"phone_attributes\": {\"attribute_name\": \"attribute_value\"}}"
                ),
                Arguments.of(
                    new NumberNode(
                        "phone",
                        new BigDecimal("1.0"),
                        List.of(
                            new Attribute("attribute_name", "attribute_value"),
                            new Attribute("attribute_name2", "attribute_value2")
                        )
                    ),
                    "{\"phone\": 1.0, \"phone_attributes\": " +
                        "{\"attribute_name\": \"attribute_value\", \"attribute_name2\": \"attribute_value2\"}}"
                ),
                //Object
                Arguments.of(new ObjectNode(List.of()), "{}"),
                Arguments.of(new ObjectNode(List.of(new NullNode("id"))), "{\"id\": null}"),
                Arguments.of(
                    new ObjectNode(List.of(new BooleanNode("isActive", true))),
                    "{\"isActive\": true}"
                ),
                Arguments.of(
                    new ObjectNode(List.of(new BooleanNode("isActive", false))),
                    "{\"isActive\": false}"
                ),
                Arguments.of(
                    new ObjectNode(List.of(new StringNode("name", "text"))),
                    "{\"name\": \"text\"}"
                ),
                Arguments.of(
                    new ObjectNode(List.of(new NumberNode("id", new BigDecimal("-123")))),
                    "{\"id\": -123}"
                ),
                Arguments.of(
                    new ObjectNode(List.of(new NumberNode("id", new BigDecimal("123")))),
                    "{\"id\": 123}"
                ),
                Arguments.of(
                    new ObjectNode(List.of(new NumberNode("id", new BigDecimal("-1.0")))),
                    "{\"id\": -1.0}"
                ),
                Arguments.of(
                    new ObjectNode(List.of(new NumberNode("id", new BigDecimal("1.0")))),
                    "{\"id\": 1.0}"
                ),
                Arguments.of(
                    new ObjectNode(List.of(new ObjectNode("phone", List.of()))), "{\"phone\": {}}"
                ),
                Arguments.of(
                    new ObjectNode(List.of(new ArrayNode("phones", List.of()))),
                    "{\"phones\": []}"
                ),
                Arguments.of(
                    new ObjectNode(
                        List.of(
                            new NumberNode("id", new BigDecimal("123")),
                            new BooleanNode("isActive", true)
                        )
                    ),
                    "{\"id\": 123, \"isActive\": true}"
                ),
                Arguments.of(
                    new ObjectNode(
                        "user",
                        List.of(new StringNode("id", "123"))
                    ),
                    "{\"user\": {\"id\": \"123\"}}"
                ),
                Arguments.of(
                    new ObjectNode(
                        "user",
                        List.of(),
                        List.of(new Attribute("attribute_name", "attribute_value"))
                    ),
                    "{\"user\": {}, \"user_attributes\": {\"attribute_name\": \"attribute_value\"}}"
                ),
                Arguments.of(
                    new ObjectNode(
                        "user",
                        List.of(),
                        List.of(
                            new Attribute("attribute_name", "attribute_value"),
                            new Attribute("attribute_name2", "attribute_value2")
                        )
                    ),
                    "{\"user\": {}, \"user_attributes\": " +
                        "{\"attribute_name\": \"attribute_value\", \"attribute_name2\": \"attribute_value2\"}}"
                ),
                //ArrayNode
                Arguments.of(new ArrayNode(List.of()), "[]"),
                Arguments.of(new ArrayNode(List.of(new NullNode())), "[null]"),
                Arguments.of(new ArrayNode(List.of(new BooleanNode(true))), "[true]"),
                Arguments.of(new ArrayNode(List.of(new BooleanNode(false))), "[false]"),
                Arguments.of(new ArrayNode(List.of(new StringNode("text"))), "[\"text\"]"),
                Arguments.of(new ArrayNode(List.of(new NumberNode(new BigDecimal("-123")))), "[-123]"),
                Arguments.of(new ArrayNode(List.of(new NumberNode(new BigDecimal("123")))), "[123]"),
                Arguments.of(new ArrayNode(List.of(new NumberNode(new BigDecimal("-1.0")))), "[-1.0]"),
                Arguments.of(new ArrayNode(List.of(new NumberNode(new BigDecimal("1.0")))), "[1.0]"),
                Arguments.of(new ArrayNode(List.of(new ObjectNode(List.of()))), "[{}]"),
                Arguments.of(
                    new ArrayNode(
                        List.of(
                            new ObjectNode(
                                List.of(new NumberNode("id", new BigDecimal("123")))
                            )
                        )
                    ),
                    "[{\"id\": 123}]"
                ),
                Arguments.of(new ArrayNode(List.of(new ArrayNode(List.of()))), "[[]]"),
                Arguments.of(
                    new ArrayNode(
                        List.of(new NumberNode(new BigDecimal("123")), new NumberNode(new BigDecimal("456")))
                    ),
                    "[123, 456]"
                ),
                Arguments.of(
                    new ArrayNode(
                        "users",
                        List.of(),
                        List.of(new Attribute("attribute_name", "attribute_value"))
                    ),
                    "{\"users\": [], \"users_attributes\": {\"attribute_name\": \"attribute_value\"}}"
                ),
                Arguments.of(
                    new ArrayNode(
                        "users",
                        List.of(),
                        List.of(
                            new Attribute("attribute_name", "attribute_value"),
                            new Attribute("attribute_name2", "attribute_value2")
                        )
                    ),
                    "{\"users\": [], \"users_attributes\": " +
                        "{\"attribute_name\": \"attribute_value\", \"attribute_name2\": \"attribute_value2\"}}"
                )
            );
        }
    }

    @Test
    public void arrayItems() {
        String expected =
            "{\"productLines\": [{\"productLine\": {\"code\": 123}}, {\"productLine\": {\"code\": 456}}]}";
        Node node = new ObjectNode(
            List.of(
                new ArrayNode(
                    "productLines",
                    List.of(
                        new ObjectNode(
                            List.of(
                                new ObjectNode(
                                    "productLine",
                                    List.of(
                                        new NumberNode("code", new BigDecimal("123"))
                                    )
                                )
                            )
                        ),
                        new ObjectNode(
                            List.of(
                                new ObjectNode(
                                    "productLine",
                                    List.of(
                                        new NumberNode("code", new BigDecimal("456"))
                                    )
                                )
                            )
                        )
                    )
                )
            )
        );
        String actual = nodeToJsonMapper.convert(node);
        assertEquals(expected, actual);
    }
}
