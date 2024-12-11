package org.example.xml.to.json.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NullNodeTest {

    @Nested
    class Singleton {

        @Test
        public void sameInstance() {
            Node nullNode1 = NullNode.getInstance();
            Node nullNode2 = NullNode.getInstance();
            assertSame(nullNode1, nullNode2);
        }
    }

    @Nested
    class GetName {

        @Test
        public void name() {
            Node node = NullNode.getInstance();
            String expected = "null";
            String actual = node.getName();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class EqualsToNull {
        Node node = NullNode.getInstance();

        @Test
        public void nodeIsNotEqualsToNull() {
            assertNotNull(node);
        }
    }
//
//    @Nested
//    class Reflexivity {
//        Node node = NullNode.getInstance();
//
//        @Test
//        public void nodeIsEqualItSelf() {
//            assertTrue(node.equals(node));
//        }
//
//        @Test
//        public void hashCodeAlwaysTheSame() {
//            int hash1 = node.hashCode();
//            int hash2 = node.hashCode();
//            assertEquals(hash1, hash2);
//        }
//    }
//
//    @Nested
//    class Symmetry {
//        Node node1 = NullNode.getInstance();
//        Node node2 = NullNode.getInstance();
//
//        @Test
//        public void nodesAreSymmetric() {
//            assertTrue(node1.equals(node2));
//            assertTrue(node2.equals(node1));
//        }
//
//        @Test
//        public void hashCodeIsEquals() {
//            assertEquals(node1.hashCode(), node2.hashCode());
//        }
//    }
//
//    @Nested
//    class Transitivity {
//        Node node1 = NullNode.getInstance();
//        Node node2 = NullNode.getInstance();
//        Node node3 = NullNode.getInstance();
//
//        @Test
//        public void nodesAreTransitivity() {
//            assertTrue(node1.equals(node2));
//            assertTrue(node2.equals(node3));
//            assertTrue(node1.equals(node3));
//        }
//
//        @Test
//        public void hashCodeIsEquals() {
//            assertEquals(node1.hashCode(), node2.hashCode());
//            assertEquals(node2.hashCode(), node3.hashCode());
//            assertEquals(node1.hashCode(), node3.hashCode());
//        }
//    }
//
//    @Nested
//    class NodeEqualsTest {
//        @Test
//        public void differentClassNotEquals() {
//            Node node = NullNode.getInstance();
//            Node stringNode = new StringNode("nodeName", "value");
//            assertFalse(node.equals(stringNode));
//        }
//    }
//
//    @Nested
//    class ToStringTest {
//        @Test
//        public void nullNodeToString() {
//            Node node = NullNode.getInstance();
//            String expected = "NullNode{}";
//            assertEquals(expected, node.toString());
//        }
//    }
}
