package com.application.generator.dto;

public class PropertiesRequest {

    private String propertyName;       // name
    private String dataType;           // string
    private String relationshipType;   // NA
    private String relationshipColumnName;
    private boolean nullable;          // Yes
    private String defaultData;        // ""
    private boolean mandatoryField;    // Yes
    private String dbColumnName;       // user_name
    private int minimumLength;         // 1
    private int maximumLength;         // 200
    private boolean enableEncryption;  // Yes
    private boolean uniqueProperty;    // Yes
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	public String getRelationshipColumnName() {
		return relationshipColumnName;
	}
	public void setRelationshipColumnName(String relationshipColumnName) {
		this.relationshipColumnName = relationshipColumnName;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public String getDefaultData() {
		return defaultData;
	}
	public void setDefaultData(String defaultData) {
		this.defaultData = defaultData;
	}
	public boolean isMandatoryField() {
		return mandatoryField;
	}
	public void setMandatoryField(boolean mandatoryField) {
		this.mandatoryField = mandatoryField;
	}
	public String getDbColumnName() {
		return dbColumnName;
	}
	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}
	public int getMinimumLength() {
		return minimumLength;
	}
	public void setMinimumLength(int minimumLength) {
		this.minimumLength = minimumLength;
	}
	public int getMaximumLength() {
		return maximumLength;
	}
	public void setMaximumLength(int maximumLength) {
		this.maximumLength = maximumLength;
	}
	public boolean isEnableEncryption() {
		return enableEncryption;
	}
	public void setEnableEncryption(boolean enableEncryption) {
		this.enableEncryption = enableEncryption;
	}
	public boolean isUniqueProperty() {
		return uniqueProperty;
	}
	public void setUniqueProperty(boolean uniqueProperty) {
		this.uniqueProperty = uniqueProperty;
	}
	public PropertiesRequest(String propertyName, String dataType, String relationshipType,
			String relationshipColumnName, boolean nullable, String defaultData, boolean mandatoryField,
			String dbColumnName, int minimumLength, int maximumLength, boolean enableEncryption,
			boolean uniqueProperty) {
		super();
		this.propertyName = propertyName;
		this.dataType = dataType;
		this.relationshipType = relationshipType;
		this.relationshipColumnName = relationshipColumnName;
		this.nullable = nullable;
		this.defaultData = defaultData;
		this.mandatoryField = mandatoryField;
		this.dbColumnName = dbColumnName;
		this.minimumLength = minimumLength;
		this.maximumLength = maximumLength;
		this.enableEncryption = enableEncryption;
		this.uniqueProperty = uniqueProperty;
	}
	public PropertiesRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PropertiesRequest [propertyName=" + propertyName + ", dataType=" + dataType + ", relationshipType="
				+ relationshipType + ", relationshipColumnName=" + relationshipColumnName + ", nullable=" + nullable
				+ ", defaultData=" + defaultData + ", mandatoryField=" + mandatoryField + ", dbColumnName="
				+ dbColumnName + ", minimumLength=" + minimumLength + ", maximumLength=" + maximumLength
				+ ", enableEncryption=" + enableEncryption + ", uniqueProperty=" + uniqueProperty + "]";
	}

   
}
