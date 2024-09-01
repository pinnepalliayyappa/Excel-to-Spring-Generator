package com.application.generator.BaseFiles;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.application.generator.dto.TemplateRequest;

@Service
public class SpringInitializer {
	
	
	public static String generateSpringBootProject(String outputDir,TemplateRequest request) throws IOException {
	    String springInitializrUrl = "https://start.spring.io/starter.zip?"
	    		+ "type=maven-project&language=java&bootVersion=3.3.3&"
	    		+ "baseDir=demo&groupId="+request.getGroupId()+ "&artifactId=" + request.getArtifactId() + "&"
	    		+ "name=demo&description=Demo project for Spring Boot"
	    		+ "&packageName="+request.getPackageName()
	    		+ "&packaging=jar&javaVersion=17&dependencies=data-jpa,mysql";
	    URL url = new URL(springInitializrUrl);
	    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
	    httpConnection.setRequestMethod("GET");

	    if (httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
	        throw new IOException("Failed to connect to Spring Initializr: " + httpConnection.getResponseMessage());
	    }

	    try (InputStream inputStream = httpConnection.getInputStream()) {
	        unzip(inputStream, outputDir);
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	        throw new IOException(e.getMessage(), e);
	        
	    }

	    return "Project generated at: " + outputDir;
	}

    private static void unzip(InputStream inputStream, String outputDir) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String filePath = outputDir + File.separator + entry.getName();
                if (entry.isDirectory()) {
                    Files.createDirectories(Paths.get(filePath));
                } else {
                    Files.createDirectories(Paths.get(filePath).getParent());
                    Files.copy(zipInputStream, Paths.get(filePath));
                }
                zipInputStream.closeEntry();
            }
        }
    }
    
    private static final String INITIALIZR_METADATA_URL = "https://start.spring.io/metadata/client";

    public Map<String, Object> fetchInitializrMetadata() {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(INITIALIZR_METADATA_URL).toUriString();
        Map<String, Object> metadata = restTemplate.getForObject(url, Map.class);
        return metadata;
    }
}
