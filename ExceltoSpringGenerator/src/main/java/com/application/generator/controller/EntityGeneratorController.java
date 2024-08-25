package com.application.generator.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.generator.BaseFiles.TemplateGenerator;
import com.application.generator.dto.TemplateRequest;

@RestController
@RequestMapping("/template")
public class EntityGeneratorController {
	
	@Autowired
	TemplateGenerator templateGenerator;

    @PostMapping("/project")
    public ResponseEntity<String> generateFile(@RequestBody TemplateRequest request) {
        String filePath = templateGenerator.generateProject(request);
         if(!filePath.equals("Error generating project")) {
		return ResponseEntity.ok( filePath);
         }
         else {
        	 return ResponseEntity.internalServerError().body("Error generating project");
         }
    }
    
}
