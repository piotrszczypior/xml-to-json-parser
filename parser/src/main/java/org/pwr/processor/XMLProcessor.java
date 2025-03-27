package org.pwr.processor;

import org.json.JSONObject;
import org.pwr.visitor.XmlNode;


public interface XMLProcessor {

    JSONObject process(XmlNode rootNode);
}
