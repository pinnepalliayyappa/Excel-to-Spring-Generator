package com.application.generator.BaseFiles;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.application.generator.entity.TemplateRequest;

public class TemplateGenerator {

    public static String generateTemplate(TemplateRequest request) {
        // Define your template with placeholders
        String template = "package {{packageName}};\n\n" +
                          "public class {{className}} {\n\n" +
                          "    private String {{variableName}};\n\n" +
                          "    public {{className}}(String {{variableName}}) {\n" +
                          "        this.{{variableName}} = {{variableName}};\n" +
                          "    }\n\n" +
                          "    public String get{{variableName}}() {\n" +
                          "        return {{variableName}};\n" +
                          "    }\n" +
                          "}";

        // Replace placeholders with actual values from the request
        template = template.replace("{{packageName}}", request.getPackageName())
                           .replace("{{className}}", request.getClassName())
                           .replace("{{variableName}}", request.getVariableName());

        return template;
    }

    public static String writeToFile(String content) throws IOException {
        //String fileName = "GeneratedClass.java";
        // Specify the directory where you want to store the file
        String directory = "src/main/java/com/application/generator/entity/";
        
        // Create the directory if it doesn't exist
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        // Define the file path and name
        String fileName = directory + "HardcodedClass.java";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
        return fileName;
    }
}
