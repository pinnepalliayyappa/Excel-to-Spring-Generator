package com.application.generator.dto;

import java.util.List;

public class GeneratorRequest {
	private List<ClassesRequest> classRequest;
	private String projecttype; 
	private String javaversion; 
	private String language; 
	private String springbootversion; 
	private String groupname; 
	private String artifactname; 
	private String name; 
	private String packagename; 
	private String description; 
	private String dependencies;
	@Override
	public String toString() {
		return "GeneratorRequest [classRequest=" + classRequest + ", projecttype=" + projecttype + ", javaversion="
				+ javaversion + ", language=" + language + ", springbootversion=" + springbootversion + ", groupname="
				+ groupname + ", artifactname=" + artifactname + ", name=" + name + ", packagename=" + packagename
				+ ", description=" + description + ", dependencies=" + dependencies + "]";
	}
	public List<ClassesRequest> getClassRequest() {
		return classRequest;
	}
	public void setClassRequest(List<ClassesRequest> classRequest) {
		this.classRequest = classRequest;
	}
	public String getProjecttype() {
		return projecttype;
	}
	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}
	public String getJavaversion() {
		return javaversion;
	}
	public void setJavaversion(String javaversion) {
		this.javaversion = javaversion;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSpringbootversion() {
		return springbootversion;
	}
	public void setSpringbootversion(String springbootversion) {
		this.springbootversion = springbootversion;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getArtifactname() {
		return artifactname;
	}
	public void setArtifactname(String artifactname) {
		this.artifactname = artifactname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDependencies() {
		return dependencies;
	}
	public void setDependencies(String dependencies) {
		this.dependencies = dependencies;
	}
	public GeneratorRequest(List<ClassesRequest> classRequest, String projecttype, String javaversion, String language,
			String springbootversion, String groupname, String artifactname, String name, String packagename,
			String description, String dependencies) {
		super();
		this.classRequest = classRequest;
		this.projecttype = projecttype;
		this.javaversion = javaversion;
		this.language = language;
		this.springbootversion = springbootversion;
		this.groupname = groupname;
		this.artifactname = artifactname;
		this.name = name;
		this.packagename = packagename;
		this.description = description;
		this.dependencies = dependencies;
	}
	public GeneratorRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
