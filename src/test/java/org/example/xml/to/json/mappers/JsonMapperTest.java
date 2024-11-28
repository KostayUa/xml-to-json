package org.example.xml.to.json.mappers;
//
//import org.example.xml.to.json.model.*;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class JsonMapperTest {
//
//    @Nested
//    class BuildingObjectNode {
////        @Test
////        public void testParseObjectNode() {
////            Node expected = new ObjectNode(Map.of("id", new NumberNode(new BigDecimal(111))));
////            String json = "{\"id\": 111}";
////            Node result = JsonMapper.parse(json);
//////        assertInstanceOf(ObjectNode.class, result);
////            assertEquals(expected, result);
////        }
//
//        @Test
//        @DisplayName("Map<Sting, (Node)StringNode>")
//        public void ObjectNodeWithStringNode() {
//            String strJson = "{\"name\": \"hello\"}";
//            StringNode stringNode = new StringNode("hello");
//            Map<String, Node> map = new HashMap<>();
//            map.put("\"name\"", stringNode);
//            Node expected = new ObjectNode(map);
//
//            Node result = JsonMapper.parse(strJson);
//
//            assertEquals(expected, result);
//        }
//
//        @Test
//        @DisplayName("Map<Sting, (Node)NumberNode>")
//        public void ObjectNodeWithNumberNode() {
//            String strJson = "{\"id\": 111}";
//            NumberNode number = new NumberNode(new BigDecimal(111));
//            Map<String, Node> map = new HashMap<>();
//            map.put("\"id\"", number);
//            Node expected = new ObjectNode(map);
//
//            Node result = JsonMapper.parse(strJson);
//
//            assertEquals(expected, result);
//        }
//
//        @Test
//        @DisplayName("Map<String, (Node)BooleanNode>")
//        public void ObjectNodeWithBooleanNode() {
//            String strJson = "{\"active\": true}";
//            BooleanNode booleanNode = new BooleanNode(true);
//            Map<String, Node> map = new HashMap<>();
//            map.put("\"active\"", booleanNode);
//            Node expected = new ObjectNode(map);
//
//            Node result = JsonMapper.parse(strJson);
//
//            assertEquals(expected, result);
//        }
//
////        @Test
////        public void ObjectNodeWithAttribute() {
////            String strJson = "{\"quantity\": {\"unit\": \"pcs\"}, \"name\": \"Bob\"";
////            Attribute attribute = new Attribute("unit", "pcs");
////            List<Attribute> list = new ArrayList<>();
////            list.add(attribute);
////            Map<String, Node> map2 = new HashMap<>();
////            Map<String, Node> map = new HashMap<>();
////            map.put("quantity", new ObjectNode(map, list));
////            Node expected = new ObjectNode(map2, list);
////
////            Node result = JsonMapper.parse(strJson);
////
////            assertEquals(expected, result);}
//    }
//
//    @Nested
//    class BuildingArrayNode {
//
//        @Test
//        @DisplayName("[a, b]")
//        public void arrayNodeWithStringNode() {
//            String strJson = "[\"a\", \"b\"]";
////            StringNode stringNode = new StringNode("a");
//            List<Node> list = new ArrayList<>();
////            list.add(stringNode);
//            list.add(new StringNode("a"));
//            list.add(new StringNode("b"));
//            Node expected = new ArrayNode(list);
//
//            Node result = JsonMapper.parse(strJson);
//
//            assertEquals(expected, result);
//        }
//
//        @Test
//        @DisplayName("[1, 2]")
//        public void arrayNodeWithNumberNode() {
//            String strJson = "[1, 2]";
//            List<Node> list = new ArrayList<>();
//            list.add(new NumberNode(new BigDecimal(1)));
//            list.add(new NumberNode(new BigDecimal(2)));
//            Node expected = new ArrayNode(list);
//
//            Node result = JsonMapper.parse(strJson);
//
//            assertEquals(expected, result);
//        }
//
//        @Test
//        public void arrayNodeOfObjects() {
//            String strJson = "[{\"id\": 1}, {\"phone\": \"(093)439-0011\"}]";
//            List<Node> list = new ArrayList<>();
//            list.add(new ObjectNode(Map.of("id", new NumberNode(new BigDecimal(1)))));
//            list.add(new ObjectNode(Map.of("phone", new StringNode("(093)439-0011"))));
//            Node expected = new ArrayNode(list);
//
//            Node result = JsonMapper.parse(strJson);
//
//            assertEquals(expected, result);
//        }
//    }
//}
