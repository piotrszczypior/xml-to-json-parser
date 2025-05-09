package org.pwr.visitor;

import org.pwr.grammar.XMLParser;
import org.pwr.grammar.XMLParserBaseVisitor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;


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
            Map<String, String> attributes = new LinkedHashMap<>();

            for (XMLParser.AttributeContext attribute : ctx.attribute()) {
                XmlNode attrNode = visit(attribute);
                attributes.put(attrNode.getTagName(), attrNode.getValue());
            }
            xmlNodeBuilder.attributes(attributes);
        }

        // tag content, for instance <...>dog<...
        if (ctx.content() != null && !ctx.content().isEmpty()) {
            XmlNode contentNode = visit(ctx.content());
            if (contentNode.isLeafNode()) { // add sth like hasChildren()
                xmlNodeBuilder.value(contentNode.getValue());
            } else {
                xmlNodeBuilder.children(contentNode.getChildren());
                if (contentNode.getValue() != null) {
                    xmlNodeBuilder.value(contentNode.getValue());
                }
            }
        }

        return xmlNodeBuilder.build();
    }

    @Override
    public XmlNode visitChardata(XMLParser.ChardataContext ctx) {
        XmlNode.XmlNodeBuilder xmlNodeBuilder = XmlNode.builder();

        if (ctx.TEXT() != null && !ctx.TEXT().getText().isEmpty()) {
            xmlNodeBuilder.value(ctx.TEXT().getText().strip());
        } else if (ctx.SEA_WS() != null) {
            xmlNodeBuilder.value(null);
        }

        return xmlNodeBuilder.build();
    }

    @Override
    public XmlNode visitAttribute(XMLParser.AttributeContext ctx) {
        String attributeText = ctx.STRING().getText();
        return XmlNode.builder()
                .tagName(ctx.Name().getText())
                //because in lexer STRING is catched with "" :(
                .value(attributeText.substring(1, attributeText.length() - 1))
                .build();
    }

    @Override
    public XmlNode visitReference(XMLParser.ReferenceContext ctx) {
        String reference = ctx.getText();
        XmlNode.XmlNodeBuilder xmlNodeBuilder = XmlNode.builder();

        if (ctx.EntityRef() != null) {
            String entityReferenceType = reference.substring(1, reference.length() - 1);

            return switch (entityReferenceType) {
                case "amp" -> xmlNodeBuilder.value("&").build();
                case "lt" -> xmlNodeBuilder.value("<").build();
                case "gt" -> xmlNodeBuilder.value(">").build();
                case "quot" -> xmlNodeBuilder.value("\"").build();
                case "apos" -> xmlNodeBuilder.value("'").build();
                default -> xmlNodeBuilder.value(reference).build();
            };
        }

        if (ctx.CharRef() != null) {
            String charReferenceType = reference.substring(2, reference.length() - 1);

            try {
                int codePoint;
                if (charReferenceType.startsWith("x") || charReferenceType.startsWith("X")) {
                    codePoint = Integer.parseInt(charReferenceType.substring(1), 16);
                } else {
                    codePoint = Integer.parseInt(charReferenceType.trim());
                }
                return xmlNodeBuilder.value(String.valueOf(Character.toChars(codePoint))).build();
            } catch (IllegalArgumentException e) {
                return xmlNodeBuilder.value(reference).build();
            }
        }

        return xmlNodeBuilder.value(reference).build();
    }

    @Override
    public XmlNode visitContent(XMLParser.ContentContext ctx) {
        if (ctx.children == null) {
            return XmlNode.builder().build();
        }
        List<XmlNode> visitedChildren = ctx.children.stream()
                .map(this::visit)
                .toList();
        List<XmlNode> children = new ArrayList<>();
        StringJoiner stringJoiner = new StringJoiner(", ");

        for (XmlNode child : visitedChildren) {
            if (child.getTagName() == null) {
                if (child.getValue() != null) {
                    stringJoiner.add(child.getValue());
                }
            } else {
                children.add(child);
            }
        }

        return XmlNode.builder()
                .children(children)
                .value(stringJoiner.toString())
                .build();
    }

    @Override
    public XmlNode visitProlog(XMLParser.PrologContext ctx) {
        // Don't need that in JSON
        return null;
    }

    @Override
    public XmlNode visitMisc(XMLParser.MiscContext ctx) {
        // Don't need that in JSON
        return null;
    }
}
