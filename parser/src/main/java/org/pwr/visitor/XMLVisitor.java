package org.pwr.visitor;

import org.pwr.grammar.XMLParser;
import org.pwr.grammar.XMLParserBaseVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XMLVisitor extends XMLParserBaseVisitor<XmlNode> {

    @Override
    public XmlNode visitDocument(XMLParser.DocumentContext ctx) {
        return this.visit(ctx.element());
    }

    @Override
    public XmlNode visitElement(XMLParser.ElementContext ctx) {
        String tagName = ctx.Name().getFirst().getText();
        XmlNode.XmlNodeBuilder xmlNodeBuilder = XmlNode.builder();
        xmlNodeBuilder.tagName(tagName);

        // tag with attributes, for instance <... id="20>...
        if (ctx.attribute() != null && !ctx.attribute().isEmpty()) {
            Map<String, String> attributes = new HashMap<>();

            for (XMLParser.AttributeContext attribute : ctx.attribute()) {
                XmlNode attrNode = visit(attribute);
                attributes.put(attrNode.getTagName(), attrNode.getValue());
            }
            xmlNodeBuilder.attributes(attributes);
        }

        // tag content, for instance <...>dog<...
        if (ctx.content() != null && !ctx.content().isEmpty()) {
            XmlNode contentNode = visit(ctx.content());

            if (contentNode.getChildren().isEmpty()) { // add sth like hasChildren()
                xmlNodeBuilder.value(contentNode.getValue());
            } else {
                xmlNodeBuilder.children(contentNode.getChildren());
            }
        }

        return xmlNodeBuilder.build();
    }

    @Override
    public XmlNode visitChardata(XMLParser.ChardataContext ctx) {
        XmlNode.XmlNodeBuilder xmlNodeBuilder = XmlNode.builder();

        if (ctx.TEXT() != null) {
            xmlNodeBuilder.value(ctx.TEXT().getText());
        } else if (ctx.SEA_WS() != null) {
            xmlNodeBuilder.value(ctx.SEA_WS().getText());
        }

        return xmlNodeBuilder.build();
    }

    @Override
    public XmlNode visitAttribute(XMLParser.AttributeContext ctx) {
        return XmlNode.builder()
                .tagName(ctx.Name().getText())
                .value(ctx.STRING().getText())
                .build();
    }

    @Override
    public XmlNode visitReference(XMLParser.ReferenceContext ctx) {
        return super.visitReference(ctx);
    }

    @Override
    public XmlNode visitContent(XMLParser.ContentContext ctx) {
        XmlNode.XmlNodeBuilder nodeBuilder = XmlNode.builder();

        List<XmlNode> visitedChildren = ctx.children.stream()
                .map(this::visit)
                .toList();
        List<XmlNode> children = new ArrayList<>();

        for (XmlNode child : visitedChildren) {
            if (child.getTagName() == null) {
                nodeBuilder.value(child.getValue()); // case with multiple strings between tags no supported
            } else {
                children.add(child);
            }
        }

        nodeBuilder.children(children);

        return nodeBuilder.build();
    }

    @Override
    public XmlNode visitProlog(XMLParser.PrologContext ctx) {
        return null; // Don't need that in JSON
    }

    @Override
    public XmlNode visitMisc(XMLParser.MiscContext ctx) {
        return null; // Don't need that in JSON
    }
}
