package org.pwr;

import org.pwr.guice.GuiceInjector;


public class Main {

    public static void main(String[] args) {

        String xmlInput = """
                <root test="someattr" test2="someattr">
                    <animals gender="female">
                        <dog>
                            <name attr="test">Maja</name>
                        </dog>
                        <cat>
                            <name>Kicia</name>
                        </cat>
                    </animals>
                    <animals gender="male">
                        <dog>
                            <name attr="test, test2">Rex</name>
                        </dog>
                        <cat>
                            <name>Puszek</name>
                        </cat>
                    </animals>
                    <pets>
                        <dog>
                            <name>Rex</name>
                        </dog>
                        <cat>
                            <name>Puszek</name>
                        </cat>
                    </pets>
                </root>
                """;

        XmlToJsonConverter converter = GuiceInjector.getInjector().getInstance(XmlToJsonConverter.class);
        String st = converter.convert(xmlInput);
        System.out.println(st);
    }
}