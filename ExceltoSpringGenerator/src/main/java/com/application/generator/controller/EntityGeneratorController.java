package com.application.generator.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.generator.BaseFiles.SpringInitializer;
import com.application.generator.BaseFiles.TemplateGenerator;
import com.application.generator.dto.ClassesRequest;
import com.application.generator.dto.TemplateRequest;
import com.application.generator.service.FileReadService;

@RestController
@RequestMapping("/template")
@CrossOrigin(origins = "http://localhost:4200") 
public class EntityGeneratorController {
	
	@Autowired
	TemplateGenerator templateGenerator;
    @Autowired
    FileReadService fileReadservice;
    
    @Autowired
    SpringInitializer metadataService;
    
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
   
    
    @PostMapping("/excel")
    public List<ClassesRequest> readExcel(@RequestParam("file") MultipartFile file) throws IOException{	
    	List<ClassesRequest> result = fileReadservice.excelRead(file);
    	return result;
    }
  //Api to fetch dependencies from spring initializer.
    @GetMapping("/dependencies")
    public Map<String, Object> getDependencies() {
        return metadataService.fetchInitializrMetadata();
    }

}
