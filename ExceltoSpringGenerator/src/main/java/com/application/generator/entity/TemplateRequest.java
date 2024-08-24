package com.application.generator.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TemplateRequest {
    private String className;
    private String packageName;
    private String variableName;
    // Add more fields as required
    

    // Getters and Setters
    public String getClassName() {
    	return this.className;
    }
    public String getPackageName() {
    	return this.packageName;
    }
    public String getVariableName() {
    	return this.variableName;
    }
    public void setClassName( String classname) {
    	this.className = classname;
    }
    public void setPackageName(String packagename) {
    	this.packageName = packagename;
    }
    public void setVariableName( String variablename) {
    	this.variableName = variablename;
    }
    
}
