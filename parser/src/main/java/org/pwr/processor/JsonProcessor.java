package org.pwr.processor;

import org.pwr.visitor.XmlNode;
import org.stringtemplate.v4.ST;

public interface JsonProcessor {

    /**
     * Processes the given XML node and converts it into a JSON representation
     * using StringTemplate (ST).
     *
     * @param node the XML node to process
     * @return an ST object representing the JSON structure
     */
    ST process(XmlNode node);
}
