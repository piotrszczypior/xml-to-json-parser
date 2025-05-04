package org.pwr.processor;

import org.pwr.visitor.XmlNode;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class JsonProcessorImpl implements JsonProcessor {
    // TODO attributes

    @Override
    public ST process(XmlNode node) {
        ST jsonTemplate = process(node, true);
        return JsonTemplates.renderObject(List.of(jsonTemplate));
    }

    private ST process(XmlNode node, boolean includeTagName) {
        if (node.getChildren() == null || node.getChildren().isEmpty()) {
            // TODO: handle list without tag name
            return includeTagName
                   ? JsonTemplates.renderValue(node.getTagName(), node.getValue())
                   : JsonTemplates.renderValue(null, node.getValue());
        }
        Map<String, List<XmlNode>> childrenByTag = node.getChildren().stream()
                .collect(Collectors.groupingBy(XmlNode::getTagName));
        List<ST> objectContent = new ArrayList<>();

        for (Map.Entry<String, List<XmlNode>> entry : childrenByTag.entrySet()) {

            if (entry.getValue().size() > 1) {
                List<ST> children = entry.getValue().stream()
                        .map(n -> process(n, false))
                        .toList();

                objectContent.add(JsonTemplates.renderList(entry.getKey(), children));
            } else {
                List<ST> children = entry.getValue().stream()
                        .map(n -> process(n, true))
                        .toList();

                objectContent.add(children.getFirst());
            }
        }

        if (node.getValue() != null && !node.getValue().isEmpty()) {
            objectContent.add(JsonTemplates.renderValue(JsonConstants.TAG_VALUE, node.getValue()));
        }

        return includeTagName
               ? JsonTemplates.renderObjectWithChildren(node.getTagName(), objectContent)
               : JsonTemplates.renderObject(objectContent);
    }
}
