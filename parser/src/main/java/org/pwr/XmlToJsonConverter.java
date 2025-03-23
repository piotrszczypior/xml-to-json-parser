package org.pwr;

import org.pwr.visitor.XmlNode;

public interface XmlToJsonConverter {

    XmlNode convert(String xml);
}
