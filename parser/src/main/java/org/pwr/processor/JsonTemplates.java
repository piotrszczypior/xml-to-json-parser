package org.pwr.processor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonTemplates {

    private static final STGroup TEMPLATES = TemplateFileReader.getTemplateFile();

    public static ST renderValueWithTag(String tag, String value) {
        return TEMPLATES.getInstanceOf("tag_string")
                .add("tag", tag)
                .add("value", value);
    }

    public static ST renderValue(String value) {
        return TEMPLATES.getInstanceOf("simple_value")
                .add("value", value);
    }

    public static ST renderObjectWithTag(final String tag, final List<ST> objectContent) {
        ST content = aggregate(objectContent);

        return TEMPLATES.getInstanceOf("object_with_children")
                .add("tag", tag)
                .add("content", content);
    }

    public static ST renderObject(final List<ST> objectContent) {
        ST content = aggregate(objectContent);

        return TEMPLATES.getInstanceOf("object")
                .add("content", content);
    }

    public static ST renderList(final String tag, final List<ST> objects) {

        return TEMPLATES.getInstanceOf("list")
                .add("tag", tag)
                .add("objects", objects);
    }

    private static ST aggregate(List<ST> objects) {
        return TEMPLATES.getInstanceOf("aggregate")
                .add("elements", objects);
    }
}
