package org.pwr;

import org.pwr.guice.GuiceInjector;


public class Main {

    public static void main(String[] args) {

        String xmlInput = """
                <root>
                <animals>
                    ggwp
                    <dog><name>Maja</name></dog>
                    <cat>miau</cat>
                    testt
                </animals>
                aaa
                <animals>
                    <dog><name>Rex</name></dog>
                    <cat>miau</cat>
                    <cat>miau2</cat>
                    testt
                </animals>
                <dell>
                    <dog><name>Rex</name></dog>
                    <cat>miau</cat>
                    <cat>miau2</cat>
                    testt
                </dell>
                
                </root>
                
                """;

        XmlToJsonConverter converter = GuiceInjector.getInjector().getInstance(XmlToJsonConverter.class);
        String st = converter.convert(xmlInput);
        System.out.println(st);
    }
}