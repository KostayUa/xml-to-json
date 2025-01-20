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
                //Null
                Arguments.of(new NullNode(null), "null"),
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
                //Boolean
                Arguments.of(new BooleanNode(null, true), "true"),
                Arguments.of(new BooleanNode(null, false), "false"),
                Arguments.of(new BooleanNode("isActive", true), "{\"isActive\": true}"),
                Arguments.of(
                    new BooleanNode(
                        "isActive",
                        true,
                        List.of(new Attribute("attribute_name", "attribute_value"))
                    ),
                    "{\"isActive\": true, \"isActive_attributes\": {\"attribute_name\": \"attribute_value\"}}"
                ),
                //String
                Arguments.of(new StringNode(null, ""), "\"\""),
                Arguments.of(new StringNode(null, "   "), "\"   \""),
                Arguments.of(new StringNode(null, "text"), "\"text\""),
                Arguments.of(new StringNode("name", "text"), "{\"name\": \"text\"}"),
                Arguments.of(
                    new StringNode(
                        "name",
                        "text",
                        List.of(new Attribute("attribute_name", "attribute_value"))
                    ),
                    "{\"name\": \"text\", \"name_attributes\": {\"attribute_name\": \"attribute_value\"}}"
                ),
                //Number
                Arguments.of(new NumberNode(null, new BigDecimal("-123")), "-123"),
                Arguments.of(new NumberNode(null, new BigDecimal("123")), "123"),
                Arguments.of(new NumberNode(null, new BigDecimal("-1.0")), "-1.0"),
                Arguments.of(new NumberNode(null, new BigDecimal("1.0")), "1.0"),
                Arguments.of(new NumberNode("phone", new BigDecimal("1.0")), "{\"phone\": 1.0}"),
                Arguments.of(
                    new NumberNode(
                        "phone",
                        new BigDecimal("1.0"),
                        List.of(new Attribute("attribute_name", "attribute_value"))
                    ),
                    "{\"phone\": 1.0, \"phone_attributes\": {\"attribute_name\": \"attribute_value\"}}"
                ),
                //Object
                Arguments.of(new ObjectNode(null, List.of()), "{}"),
                Arguments.of(
                    new ObjectNode(null, List.of(new NullNode("id"))), "{\"id\": null}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null, List.of(new BooleanNode("isActive", true))
                    ),
                    "{\"isActive\": true}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null, List.of(new BooleanNode("isActive", false))
                    ),
                    "{\"isActive\": false}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null, List.of(new StringNode("name", "text"))
                    ),
                    "{\"name\": \"text\"}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null, List.of(new NumberNode("id", new BigDecimal("-123")))
                    ),
                    "{\"id\": -123}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null, List.of(new NumberNode("id", new BigDecimal("123")))
                    ),
                    "{\"id\": 123}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null,
                        List.of(new NumberNode("id", new BigDecimal("-1.0")))
                    ),
                    "{\"id\": -1.0}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null,
                        List.of(new NumberNode("id", new BigDecimal("1.0")))
                    ),
                    "{\"id\": 1.0}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null,
                        List.of(new ObjectNode("phone", List.of()))
                    ),
                    "{\"phone\": {}}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null,
                        List.of(new ArrayNode("phones", List.of()))
                    ),
                    "{\"phones\": []}"
                ),
                Arguments.of(
                    new ObjectNode(
                        null,
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
                //ArrayNode
                Arguments.of(new ArrayNode(null, List.of()), "[]"),
                Arguments.of(new ArrayNode(null, List.of(new NullNode(null))), "[null]"),
                Arguments.of(
                    new ArrayNode(null, List.of(new BooleanNode(null, true))), "[true]"
                ),
                Arguments.of(
                    new ArrayNode(null, List.of(new BooleanNode(null, false))), "[false]"
                ),
                Arguments.of(
                    new ArrayNode(null, List.of(new StringNode(null, "text"))), "[\"text\"]"
                ),
                Arguments.of(
                    new ArrayNode(
                        null,
                        List.of(new NumberNode(null, new BigDecimal("-123")))
                    ),
                    "[-123]"
                ),
                Arguments.of(
                    new ArrayNode(
                        null, List.of(new NumberNode(null, new BigDecimal("123")))
                    ),
                    "[123]"
                ),
                Arguments.of(
                    new ArrayNode(
                        null,
                        List.of(new NumberNode(null, new BigDecimal("-1.0")))
                    ),
                    "[-1.0]"
                ),
                Arguments.of(
                    new ArrayNode(
                        null,
                        List.of(new NumberNode(null, new BigDecimal("1.0")))
                    ),
                    "[1.0]"
                ),
                Arguments.of(
                    new ArrayNode(null, List.of(new ObjectNode(null, List.of()))), "[{}]"
                ),
                Arguments.of(
                    new ArrayNode(
                        null,
                        List.of(
                            new ObjectNode(null, List.of(new NumberNode("id", new BigDecimal("123"))))
                        )
                    ),
                    "[{\"id\": 123}]"
                ),
                Arguments.of(
                    new ArrayNode(null, List.of(new ArrayNode(null, List.of()))), "[[]]"
                ),
                Arguments.of(
                    new ArrayNode(
                        null,
                        List.of(
                            new NumberNode(null, new BigDecimal("123")),
                            new NumberNode(null, new BigDecimal("456"))
                        )
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
                )
            );
        }
    }
}
