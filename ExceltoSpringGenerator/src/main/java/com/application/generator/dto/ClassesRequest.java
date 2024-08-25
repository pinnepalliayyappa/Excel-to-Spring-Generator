package com.application.generator.dto;

import java.util.List;

public class ClassesRequest {
	    private String component;          
	    private String className;         
	    private String idType;             
	    private String dbSchemaName;   
	    private String dbTableName; 
	    private List<PropertiesRequest>  propertiesRequest;
		public String getComponent() {
			return component;
		}
		public void setComponent(String component) {
			this.component = component;
		}
		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
		}
		public String getIdType() {
			return idType;
		}
		public void setIdType(String idType) {
			this.idType = idType;
		}
		public String getDbSchemaName() {
			return dbSchemaName;
		}
		public void setDbSchemaName(String dbSchemaName) {
			this.dbSchemaName = dbSchemaName;
		}
		public String getDbTableName() {
			return dbTableName;
		}
		public void setDbTableName(String dbTableName) {
			this.dbTableName = dbTableName;
		}
		public List<PropertiesRequest> getPropertiesRequest() {
			return propertiesRequest;
		}
		public void setPropertiesRequest(List<PropertiesRequest> propertiesRequest) {
			this.propertiesRequest = propertiesRequest;
		}

}
