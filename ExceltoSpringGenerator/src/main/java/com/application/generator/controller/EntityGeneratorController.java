package com.application.generator.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.generator.BaseFiles.TemplateGenerator;
import com.application.generator.entity.TemplateRequest;

@RestController
@RequestMapping("/template")
public class EntityGeneratorController {

    @PostMapping("/entity")
    public ResponseEntity<String> generateFile(@RequestBody TemplateRequest request) {
        try {
            String content = TemplateGenerator.generateTemplate(request);
            String filename="StudentEntity.java";
			String filePath = TemplateGenerator.writeToFile(content,filename);
            return ResponseEntity.ok("File generated successfully: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating file");
        }
    }
    
    @PostMapping("/repository")
    public String generateRepository(@RequestBody TemplateRequest request) throws IOException {
        String repositoryContent = TemplateGenerator.generateRepositoryTemplate(request);
        return TemplateGenerator.writeToFile(repositoryContent, request.getClassName() + "Repository.java");
    }
    
    @PostMapping("/service")
    public String generateService(@RequestBody TemplateRequest request) throws IOException {
        String serviceContent = TemplateGenerator.generateServiceTemplate(request);
        return TemplateGenerator.writeToFile(serviceContent, request.getClassName() + "Service.java");
    }
    
    @PostMapping("/controller")
    public String generateController(@RequestBody TemplateRequest request) throws IOException {
        String controllerContent = TemplateGenerator.generateControllerTemplate(request);
        return TemplateGenerator.writeToFile(controllerContent, request.getClassName() + "Controller.java");
    }

}
