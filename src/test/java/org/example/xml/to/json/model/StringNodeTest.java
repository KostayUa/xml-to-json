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

public class StringNodeTest {

    @Nested
    class EqualsToNull {
        Node node = createNode(NODE_NAME1, VALUE1);

        @Test
        public void nodeIsNotEqualsToNull() {
            assertFalse(node.equals(null));
        }
    }

    @Nested
    class Reflexivity {

        @Nested
        class WithoutAttributes {

            @Nested
            class NameIsNull {
                Node node = createNode(VALUE1);

                @Test
                public void nodeIsEqualItSelf() {
                    assertTrue(node.equals(node));
                }

                @Test
                public void hashCodeAlwaysTheSame() {
                    int hash1 = node.hashCode();
                    int hash2 = node.hashCode();
                    assertEquals(hash1, hash2);
                }
            }

            @Nested
            class NameIsNotNull {
                Node node = createNode(NODE_NAME1, VALUE1);

                @Test
                public void nodeIsEqualItSelf() {
                    assertTrue(node.equals(node));
                }

                @Test
                public void hashCodeAlwaysTheSame() {
                    int hash1 = node.hashCode();
                    int hash2 = node.hashCode();
                    assertEquals(hash1, hash2);
                }
            }
        }

        @Nested
        class WithAttributes {

            @Nested
            class NameIsNotNull {
                Node node = createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));

                @Test
                public void nodeIsEqualItSelf() {
                    assertTrue(node.equals(node));
                }

                @Test
                public void hashCodeAlwaysTheSame() {
                    int hash1 = node.hashCode();
                    int hash2 = node.hashCode();
                    assertEquals(hash1, hash2);
                }
            }
        }
    }

    @Nested
    class Symmetry {

        @Nested
        class WithoutAttributes {

            @Nested
            class NameIsNull {
                Node node1 = createNode(VALUE1);
                Node node2 = createNode(VALUE1);

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
            class NameIsNotNull {
                Node node1 = createNode(NODE_NAME1, VALUE1);
                Node node2 = createNode(NODE_NAME1, VALUE1);

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
        class WithAttributes {

            @Nested
            class NameIsNotNull {
                Node node1 = createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));
                Node node2 = createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));

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
    }

    @Nested
    class Transitivity {

        @Nested
        class WithoutAttributes {

            @Nested
            class NameIsNull {
                Node node1 = createNode(VALUE1);
                Node node2 = createNode(VALUE1);
                Node node3 = createNode(VALUE1);

                @Test
                public void nodesAreTransitivity() {
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
            class NameIsNotNull {
                Node node1 = createNode(NODE_NAME1, VALUE1);
                Node node2 = createNode(NODE_NAME1, VALUE1);
                Node node3 = createNode(NODE_NAME1, VALUE1);

                @Test
                public void nodesAreTransitivity() {
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
        class WithAttributes {

            @Nested
            class NameIsNotNull {
                Node node1 = createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));
                Node node2 = createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));
                Node node3 = createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));

                @Test
                public void nodesAreTransitivity() {
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
    }

    @Nested
    class NodeEqualsTest {

        @ParameterizedTest
        @MethodSource("sourceForNodesAreNotEquals")
        public void nodesAreNotEquals(Node node1, Node node2) {
            assertFalse(node1.equals(node2));
            assertNotEquals(node1.hashCode(), node2.hashCode());
        }

        private static Stream<Arguments> sourceForNodesAreNotEquals() {
            return Stream.of(
                Arguments.of(createNode(NODE_NAME1, VALUE1), createNode(NODE_NAME1, VALUE2)),
                Arguments.of(createNode(NODE_NAME1, VALUE1), createNode(NODE_NAME2, VALUE1)),
                Arguments.of(
                    createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))),
                    createNode(NODE_NAME1, VALUE1)
                ),
                Arguments.of(
                    createNode(NODE_NAME1, VALUE1),
                    createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))),
                    createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME2, ATTRIBUTE_VALUE1)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))),
                    createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE2)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))),
                    createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME2, ATTRIBUTE_VALUE2)))
                ),
                Arguments.of(
                    createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))),
                    createNode(NODE_NAME1, VALUE2, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)))
                ),
                Arguments.of(createNode(VALUE1), createNode(VALUE2)),
                Arguments.of(createNode(VALUE1), createNode(NODE_NAME1, VALUE2)),
                Arguments.of(createNode(NODE_NAME1, VALUE1), createNode(VALUE2))
            );
        }
    }

    @Nested
    class NodeWithDifferentClass {
        @Test
        public void differentClassWithoutAttributes() {
            Node node1 = createNode(NODE_NAME1, VALUE1);
            Node node2 = new NumberNode(NODE_NAME1, new BigDecimal(1));
            assertFalse(node1.equals(node2));
        }

        @Test
        public void differentClassWithAttributes() {
            Node node1 = createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));
            Node node2 = new NumberNode(
                NODE_NAME1,
                new BigDecimal(1),
                List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1))
            );
            assertFalse(node1.equals(node2));
        }
    }

    @Nested
    class ToStringTest {
        @Test
        public void stringNodeToString() {
            Node node = createNode(NODE_NAME1, VALUE1);
            String expected = "StringNode(name='" + NODE_NAME1 + "', value='" + VALUE1 + "')";
            assertEquals(expected, node.toString());
        }

        @Test
        public void stringNodeToStingWithAttributes() {
            Node node = createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));
            String expected = "StringNode(name='" + NODE_NAME1 + "', value='" + VALUE1 + "', " +
                "attributes=[Attribute(name='" + ATTRIBUTE_NAME1 + "', value='" + ATTRIBUTE_VALUE1 + "')])";
            assertEquals(expected, node.toString());
        }
    }

    @Nested
    class ToStringTest2 {
        @Test
        public void stringNodeToString() {
            Node node = createNode(NODE_NAME1, VALUE1);
            String expected = "StringNode(name='" + NODE_NAME1 + "', value='" + VALUE1 + "')";
            assertEquals(expected, ((StringNode) node).toString2());
        }

        @Test
        public void stringNodeToStingWithAttributes() {
            Node node = createNode(NODE_NAME1, VALUE1, List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)));
            String expected = "StringNode(name='" + NODE_NAME1 + "', value='" + VALUE1 + "', " +
                "attributes=[Attribute(name='" + ATTRIBUTE_NAME1 + "', value='" + ATTRIBUTE_VALUE1 + "')])";
            assertEquals(expected, ((StringNode) node).toString2());
        }
    }

    @Nested
    class requireNotNullTest {

        @Test
        public void valueWithoutAttributeIsNull() {
            assertThrows(NullPointerException.class, () -> createNode(NODE_NAME1, null));
        }

        @Test
        public void valueWithAttributeIsNull() {
            assertThrows(
                NullPointerException.class, () -> createNode(NODE_NAME1, null,
                    List.of(new Attribute(ATTRIBUTE_NAME1, ATTRIBUTE_VALUE1)))
            );
        }

        @Test
        public void attributesIsNull() {
            assertThrows(NullPointerException.class, () -> createNode(NODE_NAME1, VALUE1, null));
        }
    }

    private static Node createNode(String value) {
        return new StringNode(value);
    }

    private static Node createNode(String name, String value) {
        return new StringNode(name, value);
    }

    private static Node createNode(String name, String value, List<Attribute> attributes) {
        return new StringNode(name, value, attributes);
    }

    private static final String ATTRIBUTE_NAME1 = "attribute_name1";
    private static final String ATTRIBUTE_NAME2 = "attribute_name2";
    private static final String ATTRIBUTE_VALUE1 = "attribute_value1";
    private static final String ATTRIBUTE_VALUE2 = "attribute_value2";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
    private static final String NODE_NAME1 = "name1";
    private static final String NODE_NAME2 = "name2";
}
