package org.pwr.processor;

import org.junit.jupiter.api.Test;
import org.pwr.visitor.XmlNode;
import org.stringtemplate.v4.ST;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JsonProcessorTest {

    private final JsonProcessor processor = new JsonProcessorImpl();

    @Test
    void process_leafNodeWithoutAttributes_correctJson() {
        // given
        XmlNode node = XmlNode.builder()
                .tagName("leaf")
                .value("value")
                .build();

        // when
        ST result = processor.process(node);

        // then
        String expectedJson = """
                {
                  "leaf": "value"
                }
                """;
        assertThat(result.render()).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    void process_leafNodeWithAttribute_correctJson() {
        // given
        XmlNode node = XmlNode.builder()
                .tagName("leaf")
                .value("value")
                .attributes(new LinkedHashMap<>(Map.of("attr1", "val1")))
                .build();

        // when
        ST result = processor.process(node);

        String expectedJson = """
                {
                  "leaf": {
                    "@attributes": [
                      "attr1": "val1"
                    ],
                    "@text": "value"
                  }
                }
                """;
        // then
        assertThat(result.render()).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    void process_nodeWithChildren_correctJson() {
        // given
        XmlNode child1 = XmlNode.builder()
                .tagName("child1")
                .value("value1")
                .build();
        XmlNode child2 = XmlNode.builder()
                .tagName("child2")
                .value("value2")
                .build();
        XmlNode parent = XmlNode.builder()
                .tagName("parent")
                .children(List.of(child1, child2))
                .build();

        // when
        ST result = processor.process(parent);

        // then
        String expectedJson = """
                {
                  "parent": {
                   "child2": "value2",
                    "child1": "value1"
                  }
                }
                """;
        assertThat(result.render()).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    void process_nodeWithDuplicateChildren_correctJson() {
        // given
        XmlNode child = XmlNode.builder()
                .tagName("child")
                .value("value")
                .build();

        XmlNode parent = XmlNode.builder()
                .tagName("parent")
                .children(List.of(child, child))
                .build();

        // when
        ST result = processor.process(parent);

        // then
        String expectedJson = """
                {
                  "parent": {
                    "child": [
                      "value",
                      "value"
                    ]
                  }
                }
                """;
        assertThat(result.render()).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    void process_nodeWithAttributesAndChildren_correctJson() {
        // given
        XmlNode child = XmlNode.builder()
                .tagName("child")
                .value("value")
                .build();

        XmlNode parent = XmlNode.builder()
                .tagName("parent")
                .attributes(Map.of("attr", "val"))
                .children(List.of(child))
                .build();

        // when
        ST result = processor.process(parent);

        // then
        String expectedJson = """
                {
                  "parent": {
                    "child": "value",
                    "@attributes": [
                      "attr": "val"
                    ]
                  }
                }
                """;
        assertThat(result.render()).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    void process_nodeWithValueAndChildren_correctJson() {
        // given
        XmlNode child = XmlNode.builder()
                .tagName("child")
                .value("value")
                .build();

        XmlNode parent = XmlNode.builder()
                .tagName("parent")
                .value("parentValue")
                .children(List.of(child))
                .build();

        // when
        ST result = processor.process(parent);

        // then
        String expectedJson = """
                {
                  "parent": {
                    "child": "value",
                    "@text": "parentValue"
                  }
                }
                """;
        assertThat(result.render()).isEqualToIgnoringWhitespace(expectedJson);
    }
}
