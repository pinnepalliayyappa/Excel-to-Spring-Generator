package com.application.generator.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.generator.dto.ClassesRequest;
import com.application.generator.dto.PropertiesRequest;
@Service
public class FileReadService {
	
	public List<ClassesRequest> excelRead (MultipartFile file) throws IOException{
		List<List<String>> excelReadData = new ArrayList<>();
		InputStream inputStream = file.getInputStream();
		 Workbook workbook = new XSSFWorkbook(inputStream);
		 Sheet sheet = workbook.getSheetAt(0);
		 Set<String> Classnames = new HashSet<String>();
		 for (Row row : sheet) {
			 int rowNum = row.getRowNum();
			 if(rowNum>0) {
				 Classnames.add(String.valueOf(row.getCell(1)));
			 }
			 List<String> rowData = new ArrayList<>();
             for (Cell cell : row) {
                 rowData.add(String.valueOf(cell));
             }
             excelReadData.add(rowData);
         }
		 List<ClassesRequest> classArray = new ArrayList<ClassesRequest>();
		 for(String classname: Classnames) {
			 ClassesRequest classes = new ClassesRequest();
			 classes.setClassName(classname);
			 List<PropertiesRequest> propertieslist = new ArrayList<PropertiesRequest>();
			 for(int i=1;i<excelReadData.size();i++) {
				 List<String> rowdata = excelReadData.get(i);
				 if(i==1) {
					 classes.setDbSchemaName(rowdata.get(9));
					 classes.setComponent(rowdata.get(0));
					 classes.setDbTableName(rowdata.get(10));
					 classes.setIdType(rowdata.get(2));
				 }
				 PropertiesRequest properties = new PropertiesRequest();
				 if(rowdata.get(1).equals(rowdata.get(1))) {
					 properties.setPropertyName(rowdata.get(3));
					 properties.setDataType(rowdata.get(4));
					 properties.setRelationshipType(rowdata.get(5));
					 properties.setNullable(Boolean.parseBoolean(rowdata.get(6)));
					 properties.setDefaultData(rowdata.get(7));
					 properties.setMandatoryField(Boolean.parseBoolean(rowdata.get(8)));
					 properties.setMinimumLength((int)(Double.parseDouble(rowdata.get(12))));
					 properties.setDbColumnName(rowdata.get(11));
					 properties.setMaximumLength((int)(Double.parseDouble(rowdata.get(12))));
					 properties.setEnableEncryption(Boolean.parseBoolean(rowdata.get(14)));
					 properties.setUniqueProperty(Boolean.parseBoolean(rowdata.get(15)));
					 
				 }

				 propertieslist.add(properties);
			 }
			 classes.setPropertiesRequest(propertieslist);
			 classArray.add(classes);
			 
		 }
		return classArray;
		
	}

}
