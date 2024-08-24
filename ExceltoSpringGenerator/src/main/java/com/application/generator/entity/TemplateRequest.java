package com.application.generator.entity;


import java.util.List;
import java.util.Map;

public class TemplateRequest {
    private String className;
    private String packageName;
    private List<Map<String, String>> columns; //storing column name and type.
	public TemplateRequest() {
		super();
		
	}
	public TemplateRequest(String className, String packageName, List<Map<String, String>> columns) {
		super();
		this.className = className;
		this.packageName = packageName;
		this.columns = columns;
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
	public List<Map<String, String>> getColumns() {
		return columns;
	}
	public void setColumns(List<Map<String, String>> columns) {
		this.columns = columns;
	}
    
    
    
    
}
