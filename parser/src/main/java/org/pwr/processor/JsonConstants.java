package org.pwr.processor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonConstants {

    // Constant representing the JSON key for text content in XML nodes
    public static final String TAG_VALUE = "@text";

    // Constant representing the JSON key for attributes content in XML nodes
    public static final String ATTRIBUTE_VALUE = "@attributes";
}
