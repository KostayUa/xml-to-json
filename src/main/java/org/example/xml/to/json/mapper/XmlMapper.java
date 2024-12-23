package org.example.xml.to.json.mapper;

import org.example.xml.to.json.exceptions.ParseXmlException;
import org.example.xml.to.json.exceptions.UnExpectedListNameNodeException;
import org.example.xml.to.json.exceptions.UnexpectedException;
import org.example.xml.to.json.model.Node;
import org.example.xml.to.json.model.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class XmlMapper {

    public Node parse(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new ByteArrayInputStream(xml.getBytes()));
            document.getDocumentElement().normalize();

            Element element = document.getDocumentElement();

            return parseElement(element);
        } catch (SAXException e) {
            throw new ParseXmlException(e);
        } catch (IOException | ParserConfigurationException e) {
            throw new UnexpectedException(e);
        }
    }

    private Node parseElement(Element element) {
        String name = element.getTagName();
        List<Attribute> attributes = parseAttributes(element);
        NodeList children = element.getChildNodes();
        if (hasAnyElementNode(children)) {
            List<Node> nodes = parseChildrenNode(children);
            int nodeType = getNodeType(nodes);
            return switch (nodeType) {
                case 0 -> new ObjectNode(name, nodes, attributes);
                case 1 -> new ArrayNode(name, nodes, attributes);
                default -> throw new UnExpectedListNameNodeException(element);
            };
        }
        return parseValueNode(name, element, attributes);
    }

    private boolean hasAnyElementNode(NodeList children) {
        int count = children.getLength();
        for (int i = 0; i < count; i++) {
            if (children.item(i).getNodeType() == Element.ELEMENT_NODE) {
                return true;
            }
        }
        return false;
    }

    private List<Attribute> parseAttributes(Element element) {
        if (!element.hasAttributes()) {
            return List.of();
        }
        NamedNodeMap elementAttributes = element.getAttributes();
        List<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < elementAttributes.getLength(); i++) {
            Attr attr = (Attr) elementAttributes.item(i);
            attributes.add(new Attribute(attr.getName(), attr.getValue()));
        }
        return attributes;
    }

    private List<Node> parseChildrenNode(NodeList children) {
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i) instanceof Element childElement) {
                nodes.add(parseElement(childElement));
            }
        }
        return nodes;
    }

    private Node parseValueNode(String name, Element element, List<Attribute> attributes) {
        String value = element.getTextContent().trim();

        if (value.isEmpty()) {
            return new NullNode(name, attributes);
        } else if (numberPattern.matcher(value).matches()) {
            return new NumberNode(name, new BigDecimal(value), attributes);
        } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return new BooleanNode(name, Boolean.parseBoolean(value), attributes);
        } else {
            return new StringNode(name, value, attributes);
        }
    }

    /**
     * -1 error;
     * 0 object;
     * 1 array;
     * if only one item -> object;
     * all unique -> object;
     * all same -> array;
     * else -> error;
     */
    private int getNodeType(List<Node> nodes) {
        if (nodes.size() == 1) {
            return 0;
        }
        boolean hasDuplicates = false;
        boolean hasUnique = false;
        String firstName = nodes.get(0).getName();
        for (int i = 1; i < nodes.size(); i++) {
            String currentName = nodes.get(i).getName();
            if (firstName.equals(currentName)) {
                hasDuplicates = true;
            } else {
                hasUnique = true;
            }
            if (hasDuplicates && hasUnique) {
                return -1;
            }
        }
        if (hasUnique) {
            return 0;
        } else {
            return 1;
        }
    }

    private static final Pattern numberPattern = Pattern.compile(
        "^(([-+])?([0-9]+))((\\.[0-9]+)?|(\\.[0-9]+)?[eE][+-]?[0-9]+)$"
    );
}
