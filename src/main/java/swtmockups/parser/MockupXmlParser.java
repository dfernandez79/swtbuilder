package swtmockups.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import swtmockups.model.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.Optional;

import static java.util.Optional.empty;

public class MockupXmlParser {
    interface ControlDescriptionFactory {
        ControlDescription create(MockupXmlParser parser, Node node);
    }

    enum Tag {
        LABEL("Label", MockupXmlParser::createLabel);

        final String nodeName;
        final ControlDescriptionFactory factory;

        Tag(String name, ControlDescriptionFactory factory) {
            this.nodeName = name;
            this.factory = factory;
        }
    }

    public Mockup parse(InputStream input) throws MockupParseException {
        Element root = parseDOM(input).getDocumentElement();
        Mockup mockup = new Mockup(optionalIntValue(root, "width"), optionalIntValue(root, "height"));

        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            controlFor(childNodes.item(i)).ifPresent(mockup::add);
        }

        return mockup;
    }

    private Optional<ControlDescription> controlFor(Node node) {
        for (Tag tag : Tag.values()) {
            if (tag.nodeName.equals(node.getNodeName())) {
                return Optional.of(tag.factory.create(this, node));
            }
        }
        return empty();
    }

    private LabelDescription createLabel(Node node) {
        return new LabelDescription(
                optionalValue(node, "id"),
                formDataExpressions(node),
                node.getAttributes().getNamedItem("text").getNodeValue());
    }

    private FormDataExpressions formDataExpressions(Node node) {
        return new FormDataExpressions(
                optionalValue(node, "top").map(this::parseFormAttachmentExpression),
                optionalValue(node, "right").map(this::parseFormAttachmentExpression),
                optionalValue(node, "bottom").map(this::parseFormAttachmentExpression),
                optionalValue(node, "left").map(this::parseFormAttachmentExpression)
        );
    }

    private FormAttachmentExpression parseFormAttachmentExpression(String expression) {
        return FormAttachmentExpressionParser.parse(expression);
    }

    private Optional<Integer> optionalIntValue(Node node, String attributeName) {
        return optionalValue(node, attributeName).map(Integer::parseInt);
    }

    private Optional<String> optionalValue(Node node, String attributeName) {
        return Optional.ofNullable(node.getAttributes().getNamedItem(attributeName)).map(Node::getNodeValue);
    }

    private Document parseDOM(InputStream input) throws MockupParseException {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input);
        } catch (Exception e) {
            throw new MockupParseException(e);
        }
    }
}
