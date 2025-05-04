package org.pwr;


public interface XmlToJsonConverter {

    /**
     * Converts the given XML string into a JSON string representation.
     *
     * @param xml the XML string to convert
     * @return the JSON string representation of the input XML
     */
    String convert(String xml);
}
