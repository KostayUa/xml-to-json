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

        Node node = createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE2)), List.of(new Attribute(KEY1, VALUE1)));

        @Test
        public void nodeIsNotEqualsToNull() {
            assertFalse(node.equals(null));
        }
    }

    @Nested
    class Reflexivity {

        Node node = createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE2)), List.of(new Attribute(KEY1, VALUE1)));

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
    class Symmetry {

        @Nested
        class WithoutAttributes {
            Node node1 = createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE2)));
            Node node2 = createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE2)));

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
            Node node1 = createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE2)), List.of(new Attribute(KEY1, VALUE1)));
            Node node2 = createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE2)), List.of(new Attribute(KEY1, VALUE1)));

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
            Node node1 = createNode(List.of(new NumberNode(VALUE4), new NumberNode(VALUE5)));
            Node node2 = createNode(List.of(new NumberNode(VALUE4), new NumberNode(VALUE5)));
            Node node3 = createNode(List.of(new NumberNode(VALUE4), new NumberNode(VALUE5)));

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
            Node node1 = createNode(List.of(new NumberNode(VALUE4), new NumberNode(VALUE5)), List.of(new Attribute(KEY1, VALUE1)));
            Node node2 = createNode(List.of(new NumberNode(VALUE4), new NumberNode(VALUE5)), List.of(new Attribute(KEY1, VALUE1)));
            Node node3 = createNode(List.of(new NumberNode(VALUE4), new NumberNode(VALUE5)), List.of(new Attribute(KEY1, VALUE1)));

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
                Arguments.of(createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE2))),
                    createNode(List.of(new StringNode(VALUE1)))),
                Arguments.of(createNode(List.of(new StringNode(VALUE1)), List.of(new Attribute(KEY1, VALUE1))),
                    createNode(List.of(new StringNode(VALUE1)))),
                Arguments.of(createNode(List.of(new StringNode(VALUE1)), List.of(new Attribute(KEY1, VALUE1))),
                    createNode(List.of(new StringNode(VALUE1)), List.of(new Attribute(KEY2, VALUE1)))),
                Arguments.of(createNode(List.of(new StringNode(VALUE1)), List.of(new Attribute(KEY1, VALUE1))),
                    createNode(List.of(new StringNode(VALUE1)), List.of(new Attribute(KEY1, VALUE2)))),
                Arguments.of(createNode(List.of(new StringNode(VALUE1)), List.of(new Attribute(KEY1, VALUE1))),
                    createNode(List.of(new StringNode(VALUE1)), List.of(new Attribute(KEY2, VALUE2)))),
                Arguments.of(createNode(List.of(new StringNode(VALUE1)), List.of(new Attribute(KEY1, VALUE1))),
                    createNode(List.of(new StringNode(VALUE2)), List.of(new Attribute(KEY1, VALUE1))))
            );
        }
    }

    @Nested
    class NodesWithDifferentClass {
        @Test
        public void differentClassWithoutAttributes() {
            Node node1 = createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE1)));
            Node node2 = new NumberNode(VALUE5);
            assertFalse(node1.equals(node2));
        }

        @Test
        public void differentClassWithAttributes() {
            Node node1 = createNode(List.of(new StringNode(VALUE1),
                new StringNode(VALUE1)), List.of(new Attribute(KEY1, VALUE1)));
            Node node2 = new NumberNode(VALUE5, List.of(new Attribute(KEY1, VALUE1)));

            assertFalse(node1.equals(node2));
        }
    }

    @Nested
    class ToStringTest {
        @Test
        public void ArrayNodeToString() {
            Node node = createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE2)));
            String expected = "ArrayNode(items=[StringNode(value='value1'), StringNode(value='value2')])";

            assertEquals(expected, node.toString());
        }

        @Test
        public void ArrayNodeToStringWithAttributes() {
            Node node = createNode(List.of(new StringNode(VALUE1), new StringNode(VALUE2)), List.of(new Attribute(KEY1, VALUE1)));
            String expected = "ArrayNode(items=[StringNode(value='value1'), StringNode(value='value2')], attributes=[Attribute(name='key1', value='value1')])";

            assertEquals(expected, node.toString());
        }
    }

    private static Node createNode(List<Node> items) {
        return new ArrayNode(items);
    }

    private static Node createNode(List<Node> items, List<Attribute> attributes) {
        return new ArrayNode(items, attributes);
    }

    private static final String KEY1 = "key1";
    private static final String KEY2 = "key2";

    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";

    private static final BigDecimal VALUE4 = new BigDecimal(1);
    private static final BigDecimal VALUE5 = new BigDecimal(2);
}
