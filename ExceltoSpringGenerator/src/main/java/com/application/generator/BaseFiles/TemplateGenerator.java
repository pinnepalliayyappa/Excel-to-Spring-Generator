package com.application.generator.BaseFiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.application.generator.dto.TemplateRequest;


@Service
public class TemplateGenerator {
	
	public String generateProject(TemplateRequest request) {
        String outputDir = "E:/DeployIngCode/GeneratedSpringProject/";

        // Step 1: Generate base Spring Boot project using Spring Initializr
        try {
            SpringInitializer.generateSpringBootProject(outputDir);
        } catch (IOException e) {
            return "Failed to generate Spring Boot project: " + e.getMessage();
        }

        // Step 2: Generate custom files inside the Spring Boot project structure
        String entityPath = generateEntityTemplate(request, outputDir);
        String repositoryPath = generateRepositoryTemplate(request, outputDir);
        String servicePath = generateServiceTemplate(request, outputDir);
        String controllerPath = generateControllerTemplate(request, outputDir);

        if (entityPath.startsWith("Error") || repositoryPath.startsWith("Error") ||
            servicePath.startsWith("Error") || controllerPath.startsWith("Error")) {
            return "Failed to generate project files.";
        }

        return "Project generated successfully at: " + outputDir;
    }

    public static String generateEntityTemplate(TemplateRequest request, String outputDir) {
        // Base template with placeholders
        String classTemplate = "package {{packageName}};\n\n" +
                               "public class {{className}} {\n\n" +
                               "{{fields}}\n\n" +
                               "{{constructor}}\n\n" +
                               "{{gettersAndSetters}}\n" +
                               "}";

        StringBuilder fieldsBuilder = new StringBuilder();
        StringBuilder constructorParams = new StringBuilder();
        StringBuilder constructorBody = new StringBuilder();
        StringBuilder gettersAndSettersBuilder = new StringBuilder();
        

        // Loop through each column to build the class dynamically
        for (Map<String, String> column : request.getColumns()) {
            String type = column.get("type");
            String name = column.get("name");

            // Build the fields
            fieldsBuilder.append("    private ").append(type).append(" ").append(name).append(";\n");

            // Build the constructor parameters and body
            constructorParams.append(type).append(" ").append(name).append(", ");
            constructorBody.append("        this.").append(name).append(" = ").append(name).append(";\n");

            // Build getters and setters
            gettersAndSettersBuilder.append("    public ").append(type).append(" get")
                                    .append(capitalize(name)).append("() {\n")
                                    .append("        return ").append(name).append(";\n")
                                    .append("    }\n\n")
                                    .append("    public void set").append(capitalize(name)).append("(")
                                    .append(type).append(" ").append(name).append(") {\n")
                                    .append("        this.").append(name).append(" = ").append(name).append(";\n")
                                    .append("    }\n\n");
        }

        // Remove trailing comma from constructor parameters
        if (constructorParams.length() > 0) {
            constructorParams.setLength(constructorParams.length() - 2);
        }

        // Build the constructor with the dynamic content
        String constructor = "    public " + request.getClassName() + "(" + constructorParams.toString() + ") {\n" +
                             constructorBody.toString() + "    }\n";

        
        String result = classTemplate.replace("{{packageName}}", request.getPackageName())
                                     .replace("{{className}}", request.getClassName())
                                     .replace("{{fields}}", fieldsBuilder.toString())
                                     .replace("{{constructor}}", constructor)
                                     .replace("{{gettersAndSetters}}", gettersAndSettersBuilder.toString());
        
		try {
			String filePath = TemplateGenerator.writeToFile(result, "/demo/src/main/java/com/example/demo/entity/" + request.getClassName() + "Entity.java");
			return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error generating project";
		}

        
    }
    
    public static String generateRepositoryTemplate(TemplateRequest request, String outputDir) {
        // Template for the repository interface
        String template = "package {{packageName}};\n\n" +
                          "import org.springframework.data.jpa.repository.JpaRepository;\n\n" +
                          "public interface {{className}}Repository extends JpaRepository<{{className}}, Long> {\n" +
                          "}";

        String replacedTemplate = template.replace("{{packageName}}", request.getPackageName() + ".repository")
                       .replace("{{className}}", request.getClassName());
        try {
			String filePath = TemplateGenerator.writeToFile(replacedTemplate, "/demo/src/main/java/com/example/demo/repo/" + request.getClassName() + "Repository.java");
			return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error generating project";
		}
    }
    
    
    public static String generateServiceTemplate(TemplateRequest request, String outputDir) {
        // Template for the service class
        String template = "package {{packageName}};\n\n" +
                          "import java.util.List;\n" +
                          "import org.springframework.beans.factory.annotation.Autowired;\n" +
                          "import org.springframework.stereotype.Service;\n\n" +
                          "@Service\n" +
                          "public class {{className}}Service {\n\n" +
                          "    @Autowired\n" +
                          "    private {{className}}Repository repository;\n\n" +
                          "    public List<{{className}}> findAll() {\n" +
                          "        return repository.findAll();\n" +
                          "    }\n\n" +
                          "    public {{className}} save({{className}} entity) {\n" +
                          "        return repository.save(entity);\n" +
                          "    }\n\n" +
                          "    public void deleteById(Long id) {\n" +
                          "        repository.deleteById(id);\n" +
                          "    }\n\n" +
                          "    public {{className}} findById(Long id) {\n" +
                          "        return repository.findById(id).orElse(null);\n" +
                          "    }\n" +
                          "}";

        String replacedTemplate =template.replace("{{packageName}}", request.getPackageName() + ".service")
                       .replace("{{className}}", request.getClassName());
        try {
			String filePath = TemplateGenerator.writeToFile(replacedTemplate, "/demo/src/main/java/com/example/demo/service/" + request.getClassName() + "Service.java");
			
			return filePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error generating project";
		}
    }
    
    
    public static String generateControllerTemplate(TemplateRequest request, String outputDir) {
        // Template for the controller class
        String template = "package {{packageName}};\n\n" +
                          "import java.util.List;\n" +
                          "import org.springframework.beans.factory.annotation.Autowired;\n" +
                          "import org.springframework.web.bind.annotation.*;\n\n" +
                          "@RestController\n" +
                          "@RequestMapping(\"/api/{{classNameLower}}s\")\n" +
                          "public class {{className}}Controller {\n\n" +
                          "    @Autowired\n" +
                          "    private {{className}}Service service;\n\n" +
                          "    @GetMapping\n" +
                          "    public List<{{className}}> getAll() {\n" +
                          "        return service.findAll();\n" +
                          "    }\n\n" +
                          "    @PostMapping\n" +
                          "    public {{className}} create(@RequestBody {{className}} entity) {\n" +
                          "        return service.save(entity);\n" +
                          "    }\n\n" +
                          "    @GetMapping(\"/{id}\")\n" +
                          "    public {{className}} getById(@PathVariable Long id) {\n" +
                          "        return service.findById(id);\n" +
                          "    }\n\n" +
                          "    @DeleteMapping(\"/{id}\")\n" +
                          "    public void delete(@PathVariable Long id) {\n" +
                          "        service.deleteById(id);\n" +
                          "    }\n" +
                          "}";

        String replacedTemplate =template.replace("{{packageName}}", request.getPackageName() + ".controller")
                       .replace("{{className}}", request.getClassName())
                       .replace("{{classNameLower}}", request.getClassName().toLowerCase());
        
        try {
			String filePath = TemplateGenerator.writeToFile(replacedTemplate, "/demo/src/main/java/com/example/demo/controller/" + request.getClassName() + "Controller.java");
			return filePath;
		} catch (IOException e) {
			
			e.printStackTrace();
			return "Error generating project";
		}
    }


    public static String writeToFile(String content, String fileName) throws IOException {
        // Specify the directory where you want to store the file
        String directory = "E:/DeployIngCode/GeneratedSpringProject"+fileName;
        
     
        File file = new File(directory);
        // Create the directories if they don't exist
        
        file.getParentFile().mkdirs(); 
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        }
        return file.toString();
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    
    
    
}
