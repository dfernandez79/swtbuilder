package swtmockups.parser;

import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import swtmockups.model.ControlDescription;
import swtmockups.model.LabelDescription;
import swtmockups.model.Mockup;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.Optional;

public class MockupXmlParser {
    public Mockup parse(InputStream input) throws MockupParseException {
        Element root = parseDOM(input).getDocumentElement();
        Mockup mockup = new Mockup(attributeIntValue(root, "width"), attributeIntValue(root, "height"));

        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            controlFor(childNodes.item(i)).ifPresent(mockup::add);
        }

        return mockup;
    }

    private Optional<ControlDescription> controlFor(Node node) {
        if ("Label".equals(node.getNodeName())) {
            return Optional.of(
                    new LabelDescription(
                            attributeValue(node, "id"),
                            attributeValue(node, "text"),
                            formData(node)));
        }
        return Optional.empty();
    }

    private Optional<FormData> formData(Node node) {
        FormData formData = new FormData();
        attributeValue(node, "top").map(this::parseAttachment).ifPresent(attachment -> formData.top = attachment);
        attributeValue(node, "left").map(this::parseAttachment).ifPresent(attachment -> formData.left = attachment);
        return Optional.of(formData);
    }

    private FormAttachment parseAttachment(String attachmentString) {
        return new FormAttachment(Integer.parseInt(attachmentString));
    }

    private Optional<Integer> attributeIntValue(Node node, String attributeName) {
        return attributeValue(node, attributeName).map(Integer::parseInt);
    }

    private Optional<String> attributeValue(Node node, String attributeName) {
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
