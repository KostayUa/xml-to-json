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
        Node node = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)));

        @Test
        public void nodeIsNotEqualsToNull() {
            assertFalse(node.equals(null));
        }
    }

    @Nested
    class Reflexivity {

        @Nested
        class WithoutAttributes {
            Node node = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)));

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
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
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
            Node node1 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)));
            Node node2 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)));

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
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
            );
            Node node2 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
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
            Node node1 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)));
            Node node2 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)));
            Node node3 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)));

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
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
            );
            Node node2 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
            );
            Node node3 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
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
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1))),
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE2)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1))),
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1))),
                    createNode(NODE_NAME2, List.of(new StringNode(NODE_NAME2, VALUE1)))
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1), new StringNode(NODE_NAME2, VALUE2))
                    ),
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)))
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
                    ),
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1))),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
                    )
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
                    ),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)), List.of(new Attribute(KEY2, VALUE1))
                    )
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
                    ),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)), List.of(new Attribute(KEY1, VALUE2))
                    )
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
                    ),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)), List.of(new Attribute(KEY2, VALUE2))
                    )
                ),
                Arguments.of(
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
                    ),
                    createNode(
                        NODE_NAME1, List.of(new StringNode(NODE_NAME1, VALUE2)), List.of(new Attribute(KEY1, VALUE1))
                    )
                )
            );
        }
    }

    @Nested
    class NodesWithDifferentClass {
        @Test
        public void differentClassWithoutAttributes() {
            Node node1 = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)));
            Node node2 = new NumberNode(NODE_NAME1, VALUE3);
            assertFalse(node1.equals(node2));
        }

        @Test
        public void differentClassWithAttributes() {
            Node node1 = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
            );
            Node node2 = new NumberNode(NODE_NAME1, VALUE3, List.of(new Attribute(KEY1, VALUE1)));
            assertFalse(node1.equals(node2));
        }
    }

    @Nested
    class ToStringTest {
        @Test
        public void arrayNodeToString() {
            Node node = createNode(NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)));
            String expected = "ArrayNode(name='" + NODE_NAME1 + "', " +
                "items=[StringNode(name='" + NODE_NAME2 + "', value='" + VALUE1 + "')])";
            assertEquals(expected, node.toString());
        }

        @Test
        public void arrayNodeToStringWithAttributes() {
            Node node = createNode(
                NODE_NAME1, List.of(new StringNode(NODE_NAME2, VALUE1)), List.of(new Attribute(KEY1, VALUE1))
            );
            String expected = "ArrayNode(name='" + NODE_NAME1 + "', " +
                "items=[StringNode(name='" + NODE_NAME2 + "', value='" + VALUE1 + "')], " +
                "attributes=[Attribute(name='" + KEY1 + "', value='" + VALUE1 + "')])";
            assertEquals(expected, node.toString());
        }
    }

    private static Node createNode(String nodeName, List<Node> items) {
        return new ArrayNode(nodeName, items);
    }

    private static Node createNode(String nodeName, List<Node> items, List<Attribute> attributes) {
        return new ArrayNode(nodeName, items, attributes);
    }

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
    private static final String NODE_NAME1 = "name1";
    private static final String NODE_NAME2 = "name2";
    private static final BigDecimal VALUE3 = new BigDecimal(1);
}
