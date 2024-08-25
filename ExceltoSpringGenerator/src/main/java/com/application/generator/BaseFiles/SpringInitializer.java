package com.application.generator.BaseFiles;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SpringInitializer {
	public static String generateSpringBootProject(String outputDir) throws IOException {
	    String springInitializrUrl = "https://start.spring.io/starter.zip?type=maven-project&language=java&bootVersion=3.3.3&baseDir=demo&groupId=com.example&artifactId=demo&name=demo&description=Demo project for Spring Boot&packageName=com.example.demo&packaging=jar&javaVersion=17&dependencies=data-jpa,mysql";
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
	        throw new IOException("Failed  " + e.getMessage(), e);
	        
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
}
