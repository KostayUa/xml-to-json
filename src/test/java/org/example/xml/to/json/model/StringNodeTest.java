package org.example.xml.to.json.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringNodeTest {
    @Nested
    class NodeEqualsTest {
        @Test
        @DisplayName("Reflexivity")
        public void nodeIsEqualItSelf() {
            Node node = new StringNode("hello");
            assertTrue(node.equals(node));
        }

        @Test
        public void nodeIsNotEqual() {
            Node node1 = new StringNode("hello");
            Node node2 = new StringNode("world");
            assertFalse(node1.equals(node2));
        }

        @Test
        @DisplayName("Symmetry")
        public void nodeIsSymmetric() {
            Node node1 = new StringNode("equals");
            Node node2 = new StringNode("equals");
            assertTrue(node1.equals(node2));
            assertTrue(node2.equals(node1));
        }

        @Test
        @DisplayName("Transitivity")
        public void nodeIsEqualTransitive() {
            Node node1 = new StringNode("value");
            Node node2 = new StringNode("value");
            Node node3 = new StringNode("value");
            assertTrue(node1.equals(node2));
            assertTrue(node2.equals(node3));
            assertTrue(node1.equals(node3));
        }

        @Test
        public void equalsStringNodeWithAttributes() {
            Node node = new StringNode("StringValue", List.of(new Attribute("key", "value")));
            Node node2 = new StringNode("StringValue", List.of(new Attribute("key", "value")));
            assertTrue(node.equals(node2));
        }

        @Test
        public void equalsStringNodeWithAttributes2() {
            Node node = new StringNode("StringValue", List.of(new Attribute("key", "value")));
            Node node2 = new StringNode("StringValue");
            assertFalse(node.equals(node2));
        }

        @Test
        public void equalsStringNodeWithAttributes3() {
            Node node = new StringNode("StringValue", List.of(new Attribute("key", "value")));
            Node node2 = new StringNode("StringValue", List.of(new Attribute("unit", "value")));
            assertFalse(node.equals(node2));
        }

        @Test
        public void equalsStringNodeWithAttributes4() {
            Node node = new StringNode("StringValue", List.of(new Attribute("key", "value")));
            Node node2 = new StringNode("StringValue", List.of(new Attribute("key", "psc")));
            assertFalse(node.equals(node2));
        }

        @Test
        public void equalsStringNodeWithAttributes5() {
            Node node = new StringNode("StringValue", List.of(new Attribute("key", "value")));
            Node node2 = new StringNode("StringValue", List.of(new Attribute("unit", "psc")));
            assertFalse(node.equals(node2));
        }

        @Test
        public void differentClassWithAttributes() {
            Node node = new NumberNode(new BigDecimal(1));
            Node node2 = new StringNode("StringValue");
            assertFalse(node.equals(node2));
        }

        @Test
        public void differentClassWithAttributes2() {
            Node node = new NumberNode(new BigDecimal(1), List.of(new Attribute("key", "value")));
            Node node2 = new StringNode("StringValue", List.of(new Attribute("key", "value")));
            assertFalse(node.equals(node2));
        }
    }

    @Nested
    class NodeHashCodeTest {

        @Test
        public void hashCodeAlwaysTheSame() {
            Node node1 = new StringNode("hello");
            int hash = node1.hashCode();
            int hash2 = node1.hashCode();
            assertEquals(hash, hash2);
        }

        @Test
        public void hashCodeIsEquals() {
            Node node1 = new StringNode("hello");
            Node node2 = new StringNode("hello");
            assertEquals(node1.hashCode(), node2.hashCode());
        }

        @Test
        public void hashCodeIsNotEquals() {
            Node node1 = new StringNode("hello");
            Node node2 = new StringNode("world");
            assertNotEquals(node1.hashCode(), node2.hashCode());
        }
    }
}
