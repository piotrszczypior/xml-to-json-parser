package org.pwr.processor;

import org.pwr.visitor.XmlNode;
import org.stringtemplate.v4.ST;

public interface JsonProcessor {

    ST process(XmlNode node);
}
