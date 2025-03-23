package org.pwr;

import org.pwr.guice.GuiceInjector;
import org.pwr.visitor.XmlNode;

public class Main {

    public static void main(String[] args) {

        String xmlInput = "<book id=\"101\" genre=\"fiction\">"
                + "    <title>Best Novel</title>"
                + "    <author>Jane Doe</author>"
                + "    <publication>"
                + "        <publisher>Fiction House</publisher>"
                + "        <year>2021</year>"
                + "    </publication>"
                + "</book>";

        XmlToJsonConverter converter = GuiceInjector.getInjector().getInstance(XmlToJsonConverter.class);
        XmlNode node = converter.convert(xmlInput);

        System.out.println(node);
    }
}