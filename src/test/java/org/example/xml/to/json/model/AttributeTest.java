package org.example.xml.to.json.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AttributeTest {
    @Nested
    class EqualsToNull {
        Attribute attribute = createAttribute(NAME1, VALUE1);

        @Test
        public void nodeIsNotEqualsToNull() {
            assertFalse(attribute.equals(null));
        }
    }

    @Nested
    class Reflexivity {
        Attribute attribute = createAttribute(NAME1, VALUE1);

        @Test
        public void nodeIsEqualItSelf() {
            assertTrue(attribute.equals(attribute));
        }

        @Test
        public void hashCodeAlwaysTheSame() {
            int hash1 = attribute.hashCode();
            int hash2 = attribute.hashCode();
            assertEquals(hash1, hash2);
        }
    }

    @Nested
    class Symmetry {
        Attribute attribute1 = createAttribute(NAME1, VALUE1);
        Attribute attribute2 = createAttribute(NAME1, VALUE1);

        @Test
        public void nodesAreSymmetric() {
            assertTrue(attribute1.equals(attribute2));
            assertTrue(attribute2.equals(attribute1));
        }

        @Test
        public void hashCodeIsEquals() {
            assertEquals(attribute1.hashCode(), attribute2.hashCode());
        }
    }

    @Nested
    class Transitivity {
        Attribute attribute1 = createAttribute(NAME1, VALUE1);
        Attribute attribute2 = createAttribute(NAME1, VALUE1);
        Attribute attribute3 = createAttribute(NAME1, VALUE1);

        @Test
        public void nodesAreTransitivity() {
            assertTrue(attribute1.equals(attribute2));
            assertTrue(attribute2.equals(attribute3));
            assertTrue(attribute1.equals(attribute3));
        }

        @Test
        public void hashCodeIsEquals() {
            assertEquals(attribute1.hashCode(), attribute2.hashCode());
            assertEquals(attribute2.hashCode(), attribute3.hashCode());
            assertEquals(attribute1.hashCode(), attribute3.hashCode());
        }
    }

    @Nested
    class NodeEqualsTest {

        @ParameterizedTest
        @MethodSource("sourceForNodesAreNotEquals")
        public void nodesAreNotEquals(Attribute attribute1, Attribute attribute2) {
            assertFalse(attribute1.equals(attribute2));
            assertNotEquals(attribute1.hashCode(), attribute2.hashCode());
        }

        private static Stream<Arguments> sourceForNodesAreNotEquals() {
            return Stream.of(
                Arguments.of(
                    createAttribute(NAME1, VALUE1),
                    createAttribute(NAME2, VALUE1)),
                Arguments.of(
                    createAttribute(NAME1, VALUE1),
                    createAttribute(NAME1, VALUE2)),
                Arguments.of(
                    createAttribute(NAME1, VALUE1),
                    createAttribute(NAME2, VALUE2))
            );
        }
    }

    @Nested
    class NodeWithDifferentClass {
        @Test
        public void differentClassWithoutAttributes() {
            Attribute attribute1 = createAttribute(NAME1, VALUE1);
            Node node2 = new StringNode(NODE_NAME1, VALUE1);
            assertFalse(attribute1.equals(node2));
        }
    }

    @Nested
    class ToStringTest {
        @Test
        public void attributeNodeToString() {
            Attribute attribute = createAttribute(NAME1, VALUE1);
            String expected = "Attribute(name='" + NAME1 + "', value='" + VALUE1 + "')";
            assertEquals(expected, attribute.toString());
        }
    }

    private static Attribute createAttribute(String name, String value) {
        return new Attribute(name, value);
    }

    private static final String NAME1 = "name1";
    private static final String NAME2 = "name2";
    private static final String NODE_NAME1 = "nodeName1";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
}
