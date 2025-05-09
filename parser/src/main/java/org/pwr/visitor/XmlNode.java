package org.pwr.visitor;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
@Builder
public class XmlNode {

    private String tagName;

    private String value;

    private Map<String, String> attributes;

    private List<XmlNode> children;

    public boolean isLeafNode() {
        return children == null || children.isEmpty();
    }

    public boolean hasAttributes() {
        return (attributes != null && !attributes.isEmpty());
    }
}
