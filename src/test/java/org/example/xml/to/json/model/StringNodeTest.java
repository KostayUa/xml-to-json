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
        Node node = createNode(VALUE1);

        @Test
        public void nodeIsNotEqualsToNull() {
            assertFalse(node.equals(null));
        }
    }

    @Nested
    class Reflexivity {

        @Nested
        class WithoutAttributes {
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
        class WithAttributes {
            Node node = createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)));

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
    class Symmetry {

        @Nested
        class WithoutAttributes {
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
        class WithAttributes {
            Node node1 = createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)));
            Node node2 = createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)));

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
        class WithAttributes {
            Node node1 = createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)));
            Node node2 = createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)));
            Node node3 = createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)));

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
    class NodeEqualsTest {

        @ParameterizedTest
        @MethodSource("sourceForNodesAreNotEquals")
        public void nodesAreNotEquals(Node node1, Node node2) {
            assertFalse(node1.equals(node2));
            assertNotEquals(node1.hashCode(), node2.hashCode());
        }

        private static Stream<Arguments> sourceForNodesAreNotEquals() {
            return Stream.of(
                Arguments.of(createNode(VALUE1), createNode(VALUE2)),
                Arguments.of(createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1))), createNode(VALUE1)),
                Arguments.of(createNode(VALUE1), createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)))),
                Arguments.of(
                    createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1))),
                    createNode(VALUE1, List.of(new Attribute(KEY2, VALUE1)))
                ),
                Arguments.of(
                    createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1))),
                    createNode(VALUE1, List.of(new Attribute(KEY1, VALUE2)))
                ),
                Arguments.of(
                    createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1))),
                    createNode(VALUE1, List.of(new Attribute(KEY2, VALUE2)))
                ),
                Arguments.of(
                    createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1))),
                    createNode(VALUE2, List.of(new Attribute(KEY1, VALUE1)))
                )
            );
        }
    }

    @Nested
    class NodeWithDifferentClass {
        @Test
        public void differentClassWithoutAttributes() {
            Node node1 = createNode(VALUE1);
            Node node2 = new NumberNode(new BigDecimal(1));
            assertFalse(node1.equals(node2));
        }

        @Test
        public void differentClassWithAttributes() {
            Node node1 = createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)));
            Node node2 = new NumberNode(new BigDecimal(1), List.of(new Attribute(KEY1, VALUE1)));
            assertFalse(node1.equals(node2));
        }
    }

    @Nested
    class ToStringTest {
        @Test
        public void StringNodeToString() {
            Node node = createNode(VALUE1);
            String expected = "StringNode(value='value1')";
            assertEquals(expected, node.toString());
        }

        @Test
        public void StringNodeToStingWithAttributes() {
            Node node = createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)));
            String expected = "StringNode(value='value1', attributes=[Attribute(name='key1', value='value1')])";
            assertEquals(expected, node.toString());
        }
    }

    @Nested
    class ToStringTest2 {
        @Test
        public void StringNodeToString() {
            Node node = createNode(VALUE1);
            String expected = "StringNode(value='value1')";
            assertEquals(expected, ((StringNode) node).toString2());
        }

        @Test
        public void StringNodeToStingWithAttributes() {
            Node node = createNode(VALUE1, List.of(new Attribute(KEY1, VALUE1)));
            String expected = "StringNode(value='value1', attributes=[Attribute(name='key1', value='value1')])";
            assertEquals(expected, ((StringNode) node).toString2());
        }
    }

    private static Node createNode(String value) {
        return new StringNode(value);
    }

    private static Node createNode(String value, List<Attribute> attributes) {
        return new StringNode(value, attributes);
    }

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";

    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
}
