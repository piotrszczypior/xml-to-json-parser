package org.pwr.processor;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupString;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateFileReader {

    private static final String TEMPLATE_FILE = "templates/json.stg";

    public static STGroup getTemplateFile() {
        try (InputStream is = TemplateFileReader.class.getClassLoader().getResourceAsStream(TEMPLATE_FILE)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + TEMPLATE_FILE);
            }
            String template = new Scanner(is, StandardCharsets.UTF_8)
                    .useDelimiter("\\A")
                    .next();

            return new STGroupString(template);
        } catch (IOException e) {
            throw new TemplateFileException("Failed to load template file", e);
        }
    }
}
