package org.example.xml.to.json.mapper;

import org.example.xml.to.json.model.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonMapperTest {
    private final JsonMapper jsonMapper = new JsonMapper();

    @ParameterizedTest
    @MethodSource("sourceForJsonTransformationToNode")
    public void jsonMapper(String json, Node expected) {
        Node actual = jsonMapper.parse(json);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> sourceForJsonTransformationToNode() {
        return Stream.of(
            //Null
            Arguments.of("null", new NullNode(null)),
            //Boolean
            Arguments.of("true", new BooleanNode(null, true)),
            Arguments.of("false", new BooleanNode(null, false)),
            //String
            Arguments.of("\"\"", new StringNode(null, "")),
            Arguments.of("\"   \"", new StringNode(null, "   ")),
            Arguments.of("\"text\"", new StringNode(null, "text")),
            //Number
            Arguments.of("-123", new NumberNode(null, new BigDecimal("-123"))),
            Arguments.of("123", new NumberNode(null, new BigDecimal("123"))),
            Arguments.of("-1.0", new NumberNode(null, new BigDecimal("-1.0"))),
            Arguments.of("1.0", new NumberNode(null, new BigDecimal("1.0"))),
            //Object
            Arguments.of("{}", new ObjectNode(null, List.of())),
            Arguments.of("{\"id\": null}", new ObjectNode(null, List.of(new NullNode("id")))),
            Arguments.of(
                "{\"isActive\": true}",
                new ObjectNode(null, List.of(new BooleanNode("isActive", true)))
            ),
            Arguments.of(
                "{\"isActive\": false}",
                new ObjectNode(null, List.of(new BooleanNode("isActive", false)))
            ),
            Arguments.of(
                "{\"name\": \"text\"}",
                new ObjectNode(null, List.of(new StringNode("name", "text")))
            ),
            Arguments.of(
                "{\"id\": -123}",
                new ObjectNode(null, List.of(new NumberNode("id", new BigDecimal("-123"))))
            ),
            Arguments.of(
                "{\"id\": 123}",
                new ObjectNode(null, List.of(new NumberNode("id", new BigDecimal("123"))))
            ),
            Arguments.of(
                "{\"id\": -1.0}",
                new ObjectNode(null, List.of(new NumberNode("id", new BigDecimal("-1.0"))))
            ),
            Arguments.of(
                "{\"id\": 1.0}",
                new ObjectNode(null, List.of(new NumberNode("id", new BigDecimal("1.0"))))
            ),
            Arguments.of(
                "{\"phone\": {}}",
                new ObjectNode(null, List.of(new ObjectNode("phone", List.of())))
            ),
            Arguments.of(
                "{\"phones\": []}",
                new ObjectNode(null, List.of(new ArrayNode("phones", List.of())))
            ),
            Arguments.of(
                "{\"id\": 123, \"isActive\": true}",
                new ObjectNode(
                    null,
                    List.of(
                        new NumberNode("id", new BigDecimal("123")),
                        new BooleanNode("isActive", true)
                    )
                )
            ),
            //Array
            Arguments.of("[]", new ArrayNode(null, List.of())),
            Arguments.of("[null]", new ArrayNode(null, List.of(new NullNode(null)))),
            Arguments.of(
                "[true]",
                new ArrayNode(null, List.of(new BooleanNode(null, true)))
            ),
            Arguments.of(
                "[false]",
                new ArrayNode(null, List.of(new BooleanNode(null, false)))
            ),
            Arguments.of(
                "[\"text\"]",
                new ArrayNode(null, List.of(new StringNode(null, "text")))
            ),
            Arguments.of(
                "[-123]",
                new ArrayNode(null, List.of(new NumberNode(null, new BigDecimal("-123"))))
            ),
            Arguments.of(
                "[123]",
                new ArrayNode(null, List.of(new NumberNode(null, new BigDecimal("123"))))
            ),
            Arguments.of(
                "[-1.0]",
                new ArrayNode(null, List.of(new NumberNode(null, new BigDecimal("-1.0"))))
            ),
            Arguments.of(
                "[1.0]",
                new ArrayNode(null, List.of(new NumberNode(null, new BigDecimal("1.0"))))
            ),
            Arguments.of("[{}]", new ArrayNode(null, List.of(new ObjectNode(null, List.of())))),
            Arguments.of(
                "[{\"id\": 123}]",
                new ArrayNode(
                    null,
                    List.of(
                        new ObjectNode(null, List.of(new NumberNode("id", new BigDecimal("123"))))
                    )
                )
            ),
            Arguments.of("[[]]", new ArrayNode(null, List.of(new ArrayNode(null, List.of())))),
            Arguments.of(
                "[123, 456]",
                new ArrayNode(
                    null,
                    List.of(
                        new NumberNode(null, new BigDecimal("123")),
                        new NumberNode(null, new BigDecimal("456"))
                    )
                )
            )
        );
    }
}
