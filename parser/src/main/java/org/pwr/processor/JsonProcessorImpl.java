package org.pwr.processor;

import org.pwr.visitor.XmlNode;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class JsonProcessorImpl implements JsonProcessor {

    @Override
    public ST process(XmlNode node) {
        // First object include tag name - as a root object
        ST jsonTemplate = process(node, true);
        return JsonTemplates.renderObject(List.of(jsonTemplate));
    }

    private ST process(XmlNode node, boolean includeTagName) {
        if (node.isLeafNode()) {

            if (node.hasAttributes()) {
                return processLeafWithAttributes(node);
            }

            return includeTagName
                    ? JsonTemplates.renderValueWithTag(node.getTagName(), node.getValue())
                    : JsonTemplates.renderValue(node.getValue());
        }
        List<ST> objectContent = processDuplicatesInListsAndVisitChildren(node);

        if (node.getValue() != null && !node.getValue().isEmpty()) {
            objectContent.add(JsonTemplates.renderValueWithTag(JsonConstants.TAG_VALUE, node.getValue()));
        }

        if (node.hasAttributes()) {
            List<ST> processedAttributes = node.getAttributes().entrySet().stream().map(pair -> JsonTemplates.renderValueWithTag(pair.getKey(), pair.getValue())).toList();
            objectContent.add(JsonTemplates.renderList(JsonConstants.ATTRIBUTE_VALUE, processedAttributes));
        }

        return includeTagName
                ? JsonTemplates.renderObjectWithTag(node.getTagName(), objectContent)
                : JsonTemplates.renderObject(objectContent);
    }

    private ST processLeafWithAttributes(XmlNode node) {
        List<ST> objectContent = new ArrayList<>();
        objectContent.add(processAttributes(node));
        objectContent.add(JsonTemplates.renderValueWithTag(JsonConstants.TAG_VALUE, node.getValue()));

        return JsonTemplates.renderObjectWithTag(node.getTagName(), objectContent);
    }

    private List<ST> processDuplicatesInListsAndVisitChildren(XmlNode node) {
        Map<String, List<XmlNode>> childrenByTag = node.getChildren().stream()
                .collect(Collectors.groupingBy(XmlNode::getTagName));
        List<ST> objectContent = new ArrayList<>();

        for (Map.Entry<String, List<XmlNode>> entry : childrenByTag.entrySet()) {

            if (shouldAggregateObjectsIntoList(entry)) {
                List<ST> children = entry.getValue().stream()
                        .map(n -> process(n, false))
                        .toList();
                objectContent.add(JsonTemplates.renderList(entry.getKey(), children));

            } else {

                // After grouping by tag and aggregating multiple objects,
                // it is assumed that there is another object with different tag.
                List<ST> children = entry.getValue().stream()
                        .map(n -> process(n, true))
                        .toList();
                objectContent.add(children.getFirst());
            }
        }

        return objectContent;
    }

    private ST processAttributes(XmlNode node) {
        List<ST> processedAttributes = node.getAttributes().entrySet().stream()
                .map(pair -> JsonTemplates.renderValueWithTag(pair.getKey(), pair.getValue()))
                .toList();
        return JsonTemplates.renderList(JsonConstants.ATTRIBUTE_VALUE, processedAttributes);
    }

    private boolean shouldAggregateObjectsIntoList(Map.Entry<String, List<XmlNode>> entry) {
        return entry.getValue().size() > 1;
    }
}
