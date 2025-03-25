package org.pwr;

import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.json.JSONObject;
import org.pwr.grammar.XMLLexer;
import org.pwr.grammar.XMLParser;
import org.pwr.guice.GuiceInjector;
import org.pwr.processor.XMLProcessor;
import org.pwr.visitor.XMLVisitor;
import org.pwr.visitor.XmlNode;


@RequiredArgsConstructor(onConstructor_ = @Inject)
public class XmlToJsonConverterImpl implements XmlToJsonConverter {

    private final XMLProcessor xmlProcessor;

    @Override
    public JSONObject convert(final String xml) {
        return xmlProcessor.process(parseToObject(xml));
    }

    private XmlNode parseToObject(final String xml) {
        CharStream input = CharStreams.fromString(xml);
        XMLLexer lexer = new XMLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        XMLParser parser = new XMLParser(tokens);
        ParseTree tree = parser.document();

        XMLVisitor visitor = GuiceInjector.getInjector().getInstance(XMLVisitor.class);
        return visitor.visit(tree);
    }
}
