package org.example.xml.to.json.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayNodeTest {

    @Nested
    class EqualsToNull {
        Node node = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)));

        @Test
        public void nodeIsNotEqualsToNull() {
            assertFalse(node.equals(null));
        }
    }

    @Nested
    class Reflexivity {

        @Nested
        class WithoutAttributes {
            Node node = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)));

            @Test
            public void nodeEqualsToItself() {
                assertTrue(node.equals(node));
            }

            @Test
            public void hashCodeIsConsistent() {
                int hash1 = node.hashCode();
                int hash2 = node.hashCode();
                assertEquals(hash1, hash2);
            }
        }

        @Nested
        class WithAttributes {
            Node node = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
            );

            @Test
            public void nodeEqualsToItself() {
                assertTrue(node.equals(node));
            }

            @Test
            public void hashCodeIsConsistent() {
                int hash1 = node.hashCode();
                int hash2 = node.hashCode();
                assertEquals(hash1, hash2);
            }
        }
    }

    @Nested
    class Symmetry {

        @Nested
        class WithoutAttributes {
            Node node1 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)));
            Node node2 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)));

            @Test
            public void nodesAreSymmetric() {
                assertTrue(node1.equals(node2));
                assertTrue(node2.equals(node1));
            }

            @Test
            public void hashCodeIsEquals() {
                assertEquals(node1.hashCode(), node2.hashCode());
            }
        }

        @Nested
        class WithAttributes {
            Node node1 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
            );
            Node node2 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
            );

            @Test
            public void nodesAreSymmetric() {
                assertTrue(node1.equals(node2));
                assertTrue(node2.equals(node1));
            }

            @Test
            public void hashCodeIsEquals() {
                assertEquals(node1.hashCode(), node2.hashCode());
            }
        }
    }

    @Nested
    class Transitivity {

        @Nested
        class WithoutAttributes {
            Node node1 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)));
            Node node2 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)));
            Node node3 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)));

            @Test
            public void nodeAreTransitivity() {
                assertTrue(node1.equals(node2));
                assertTrue(node2.equals(node3));
                assertTrue(node1.equals(node3));
            }

            @Test
            public void hashCodeIsEquals() {
                assertEquals(node1.hashCode(), node2.hashCode());
                assertEquals(node2.hashCode(), node3.hashCode());
                assertEquals(node1.hashCode(), node3.hashCode());
            }
        }

        @Nested
        class WithAttributes {
            Node node1 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
            );
            Node node2 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
            );
            Node node3 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
            );

            @Test
            public void nodeAreTransitivity() {
                assertTrue(node1.equals(node2));
                assertTrue(node2.equals(node3));
                assertTrue(node1.equals(node3));
            }

            @Test
            public void hashCodeIsEquals() {
                assertEquals(node1.hashCode(), node2.hashCode());
                assertEquals(node2.hashCode(), node3.hashCode());
                assertEquals(node1.hashCode(), node3.hashCode());
            }
        }
    }

    @Nested
    class NodeEqualsTest {

        @ParameterizedTest
        @MethodSource("sourceForNodeAreNotEquals")
        public void nodesAreNotEquals(Node node1, Node node2) {
            assertFalse(node1.equals(node2));
            assertNotEquals(node1.hashCode(), node2.hashCode());
        }

        private static Stream<Arguments> sourceForNodeAreNotEquals() {
            return Stream.of(
                Arguments.of(
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE1))),
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1))),
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE1)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE1))),
                    createNode(NODE_NAME2, List.of(new StringNode(NODE_NAME2, STRING_VALUE1)))
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1), new StringNode(NODE_NAME2, STRING_VALUE2))
                    ),
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)))
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
                    ),
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1))),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
                    )
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
                    ),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)), List.of(new Attribute(ATTRIBUTE_NAME2, ATTRIBUTE_VALUE1))
                    )
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
                    ),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE2))
                    )
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
                    ),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)), List.of(new Attribute(ATTRIBUTE_NAME2, ATTRIBUTE_VALUE2))
                    )
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE1)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
                    ),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, STRING_VALUE2)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
                    )
                )
            );
        }
    }

    @Nested
    class NodesWithDifferentClass {
        @Test
        public void differentClassWithoutAttributes() {
            Node node1 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)));
            Node node2 = new NumberNode(NODE_NAME1, VALUE);
            assertFalse(node1.equals(node2));
        }

        @Test
        public void differentClassWithAttributes() {
            Node node1 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
            );
            Node node2 = new NumberNode(NODE_NAME1, VALUE, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));
            assertFalse(node1.equals(node2));
        }
    }

    @Nested
    class ToStringTest {
        @Test
        public void arrayNodeToString() {
            Node node = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)));
            String expected = "ArrayNode(name='" + NODE_NAME1 + "', " +
                "items=[StringNode(name='" + NODE_NAME2 + "', value='" + STRING_VALUE2 + "')])";
            assertEquals(expected, node.toString());
        }

        @Test
        public void arrayNodeToStringWithAttributes() {
            Node node = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)), List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
            );
            String expected = "ArrayNode(name='" + NODE_NAME1 + "', " +
                "items=[StringNode(name='" + NODE_NAME2 + "', value='" + STRING_VALUE2 + "')], " +
                "attributes=[Attribute(name='" + ATTRIBUTE_NAME1 + "', value='" + ATTRIBUTE_VALUE1 + "')])";
            assertEquals(expected, node.toString());
        }
    }

    @Nested
    class requireNotNullTest {

        @Test
        public void nameIsNull() {
            assertThrows(NullPointerException.class, () -> createNode(null, List.of(new StringNode(NODE_NAME1, STRING_VALUE1))));
        }

        @Test
        public void attributesIsNull() {
            assertThrows(NullPointerException.class, () -> createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, STRING_VALUE2)), null));
        }
    }

    private static Node createNode(String name, List<Node> items) {
        return new ArrayNode(name, items);
    }

    private static Node createNode(String name, List<Node> items, List<Attribute> attributes) {
        return new ArrayNode(name, items, attributes);
    }

    private static final String ATTRIBUTE_NAME1 = "attribute_name1";
    private static final String ATTRIBUTE_NAME2 = "attribute_name2";
    private static final String ATTRIBUTE_VALUE1 = "attribute_value1";
    private static final String ATTRIBUTE_VALUE2 = "attribute_value2";
    private static final String NODE_NAME1 = "name1";
    private static final String NODE_NAME2 = "name2";
    private static final BigDecimal VALUE = new BigDecimal(1);
    private static final String STRING_VALUE1 = "value1";
    private static final String STRING_VALUE2 = "value2";
}
