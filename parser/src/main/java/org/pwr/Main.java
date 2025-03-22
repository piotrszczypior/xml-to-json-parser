package org.pwr;

import com.google.inject.Inject;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.pwr.grammar.XMLLexer;
import org.pwr.grammar.XMLParser;
import org.pwr.guice.GuiceInjector;
import org.pwr.parser.XMLVisitor;

public class Main {

    public static void main(String[] args) {

//        String xmlInput = "<person><name>John</name><age>30</age></person>";
        String xmlInput = "<person>name</person>";
        CharStream input = CharStreams.fromString(xmlInput);

        XMLLexer lexer = new XMLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        XMLParser parser = new XMLParser(tokens);
        ParseTree tree = parser.document();

        XMLVisitor visitor = GuiceInjector.getInjector().getInstance(XMLVisitor.class);
        visitor.visit(tree);
    }
}