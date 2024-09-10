package com.application.generator.dto;


import java.util.List;
import java.util.Map;

public class TemplateRequest {
    private String className;
    private String packageName;
    private String artifactId;
    private String groupId;
    private List<Map<String, String>> columns; //storing column name and type.
    
    private List<PropertiesRequest> properties;
	public TemplateRequest() {
		super();
		
	}
	public TemplateRequest(String className, String packageName, String artifactId, String groupId,
			List<Map<String, String>> columns, List<PropertiesRequest> properties) {
		super();
		this.className = className;
		this.packageName = packageName;
		this.artifactId = artifactId;
		this.groupId = groupId;
		this.columns = columns;
		this.properties = properties;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public List<Map<String, String>> getColumns() {
		return columns;
	}
	public void setColumns(List<Map<String, String>> columns) {
		this.columns = columns;
	}
	public List<PropertiesRequest> getProperties() {
		return properties;
	}
	public void setProperties(List<PropertiesRequest> properties) {
		this.properties = properties;
	}
	
	
	
	
    
    
    
    
}
