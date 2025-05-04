package org.pwr.processor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonTemplates {

    // TODO: use relative path
    private static final STGroup TEMPLATES = new STGroupFile("C:\\Projects\\xml-to-json-parser\\parser\\src\\main\\resources\\templates\\json.stg");

    public static ST renderValue(String tag, String value) {

        return TEMPLATES.getInstanceOf("key_string")
                .add("_key", tag)
                .add("_value", value);
    }

    public static ST renderObjectWithChildren(final String tag, final List<ST> objectContent) {
        ST content = TEMPLATES.getInstanceOf("aggregate")
                .add("elements", objectContent);

        return TEMPLATES.getInstanceOf("object_with_children")
                .add("tag", tag)
                .add("content", content);
    }

    public static ST renderObject(final List<ST> objectContent) {
        ST content = TEMPLATES.getInstanceOf("aggregate")
                .add("elements", objectContent);

        return TEMPLATES.getInstanceOf("object")
                .add("content", content);
    }

    public static ST renderList(final String tag, final List<ST> objects) {
        return TEMPLATES.getInstanceOf("list")
                .add("tag", tag)
                .add("objects", objects);
    }
}
