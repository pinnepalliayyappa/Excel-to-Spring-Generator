package com.application.generator.dto;

import java.util.List;

public class ClassesRequest {         
	    private String className;                      
	    private String dbSchemaName;   
	    private String dbTableName; 
	    private String idType;  
	    private List<PropertiesRequest>  properties;

		public String getClassName() {
			return className;
		}
		public void setClassName(String className) {
			this.className = className;
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
		public String getIdType() {
			return idType;
		}
		public void setIdType(String idType) {
			this.idType = idType;
		}
		public List<PropertiesRequest> getProperties() {
			return properties;
		}
		public void setProperties(List<PropertiesRequest> properties) {
			this.properties = properties;
		}
		public ClassesRequest(String className, String dbSchemaName, String dbTableName,
				List<PropertiesRequest> properties) {
			super();
			this.className = className;
			this.dbSchemaName = dbSchemaName;
			this.dbTableName = dbTableName;
			this.properties = properties;
		}
		@Override
		public String toString() {
			return "ClassesRequest [className=" + className + ", dbSchemaName=" + dbSchemaName + ", dbTableName="
					+ dbTableName + ", properties=" + properties + "]";
		}
		public ClassesRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
