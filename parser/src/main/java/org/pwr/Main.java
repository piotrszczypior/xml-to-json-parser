package org.pwr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.pwr.guice.GuiceInjector;
import org.pwr.visitor.XmlNode;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        String xmlInput = """
                <?xml version="1.0" encoding="UTF-8"?>
                               <message>
                                 <header>
                                   <title>XML &amp; JSON Conversion</title>
                                   <description>Examples of &lt;references&gt; in XML</description>
                                 </header>
                                 <body>
                                   <paragraph>
                                     This is a paragraph with special characters: &lt; &gt; &amp; &quot; &apos;
                                   </paragraph>
                                   <data value="Price: &#36;100"/>
                                   <special>
                                        ddd  &#36;
                                   </special>
                                   <unicode>Unicode character: &#x263A;</unicode>
                                 </body>
                               </message>
                """;

        XmlToJsonConverter converter = GuiceInjector.getInjector().getInstance(XmlToJsonConverter.class);
        XmlNode node = converter.convert(xmlInput);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

//        JSONObject json = processor.process(node);

        System.out.println(objectMapper.writeValueAsString(node));
    }
}