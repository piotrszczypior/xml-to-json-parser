package org.pwr.processor;

import org.json.JSONObject;
import org.pwr.visitor.XmlNode;

import java.util.List;

import static org.pwr.XmlToJsonConstants.TAG_ATTRIBUTES;
import static org.pwr.XmlToJsonConstants.TAG_VALUE;


public class XMLProcessorImpl implements XMLProcessor {

    @Override
    public JSONObject process(final XmlNode rootNode) {
        XmlNode currentNode = XmlNode.builder()
                .children(List.of(rootNode))
                .build();

        return buildJsonNode(currentNode);
    }

    private JSONObject buildJsonNode(final XmlNode xmlNode) {
        JSONObject jsonObject = new JSONObject();
        if (xmlNode.getAttributes() != null && !xmlNode.getAttributes().isEmpty()) {
            jsonObject.put(TAG_ATTRIBUTES, xmlNode.getAttributes());
        }
        jsonObject.put(TAG_VALUE, xmlNode.getValue());

        if (xmlNode.getChildren() != null) {
            xmlNode.getChildren().forEach(node ->
                    jsonObject.accumulate(node.getTagName(), buildJsonNode(node)));
        }

        return jsonObject;
    }
}