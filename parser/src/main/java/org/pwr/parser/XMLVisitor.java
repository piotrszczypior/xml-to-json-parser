package org.pwr.parser;

import org.json.JSONException;
import org.json.JSONObject;
import org.pwr.grammar.XMLParser;
import org.pwr.grammar.XMLParserBaseVisitor;


public class XMLVisitor extends XMLParserBaseVisitor<Object> {

    @Override
    public Object visitDocument(XMLParser.DocumentContext ctx) {
        return this.visit(ctx.element());
    }

    @Override
    public Object visitElement(XMLParser.ElementContext ctx) {
        String tagName = ctx.Name().getFirst().getText();

        // tag with attributes
        if (ctx.attribute() != null && !ctx.attribute().isEmpty()) {

        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(tagName, this.visit(ctx.content()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject);

        return jsonObject;
    }

    @Override
    public Object visitChardata(XMLParser.ChardataContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitAttribute(XMLParser.AttributeContext ctx) {
        return super.visitAttribute(ctx);
    }

    @Override
    public Object visitReference(XMLParser.ReferenceContext ctx) {
        return super.visitReference(ctx);
    }

    @Override
    public Object visitContent(XMLParser.ContentContext ctx) {
        return this.visit(ctx.chardata().getFirst());
    }

    @Override
    public Object visitProlog(XMLParser.PrologContext ctx) {
        return null; // Don't need that in JSON
    }

    @Override
    public Object visitMisc(XMLParser.MiscContext ctx) {
        return null; // Don't need that in JSON
    }
}
