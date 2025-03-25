package org.pwr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.pwr.guice.GuiceInjector;


public class Main {

    public static void main(String[] args) {

        // String xmlInput = """
        //         <?xml version="1.0" encoding="UTF-8"?>
        //                        <message>
        //                          <header>
        //                            <title>XML &amp; JSON Conversion</title>
        //                            <description>Examples of &lt;references&gt; in XML</description>
        //                          </header>
        //                          <body>
        //                            <paragraph>
        //                              This is a paragraph with special characters: &lt; &gt; &amp; &quot; &apos;
        //                            </paragraph>
        //                            <data value="Price: &#36;100"/>
        //                            <special>
        //                                 ddd
        //                            </special>
        //                            <unicode>Unicode character: &#x263A;</unicode>
        //                          </body>
        //                        </message>
        //         """;
        String xmlInput = """
                <?xml version="1.0" encoding="UTF-8"?>
                            <root>
                                <library a="second">
                                    <book></book>
                                    <book>wadwad
                                    </book>
                                </library>
                                <book c="third">
                                    awdwad
                                </book>
                                    dadadas
                                <empty></empty>
                                <empty2/>
                            </root>
                """;
        XmlToJsonConverter converter = GuiceInjector.getInjector().getInstance(XmlToJsonConverter.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        System.out.printf(converter.convert(xmlInput).toString());
    }
}