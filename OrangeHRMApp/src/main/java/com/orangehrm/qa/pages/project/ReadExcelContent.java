package com.orangehrm.qa.pages.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelContent {

	public static String[][] getDataSheetTCData(String workBook, String sheetName, String testcaseName){
		
		String filePaths[] = workBook.split("/");
		String finalPath = filePaths[filePaths.length - 1];
		
		String[][] array2 = null;
		String filterValue[][] = null;
		
		try {
			FileInputStream fis = new FileInputStream(new File("./TestData/testData.xlsx"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	//Below method connects to the excel sheet and reads each row one by one
	//This method will store the output in HashMap(key in String - column header, values  
	
	public static Map<String, ArrayList<String>> readExcelAllColumns(String colName1, String colName2, String colName3, String colName4){
		
		Map<String, ArrayList<String>> multimapData = new LinkedHashMap<String, ArrayList<String>>();
		try {
			FileInputStream fis = new FileInputStream(new File("./TestData/testData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet("input Sheet");
			int lastRowNum = sheet.getLastRowNum();
			
			DataFormatter formatter = new DataFormatter();
			Map<String, Integer> indexMap = new LinkedHashMap<String, Integer>();
			indexMap.clear();
			Row firstRow = sheet.getRow(0); //get first row
			int maxColIndex = firstRow.getLastCellNum(); //get the last colum index for first row
			
			for(int colIndex=0;colIndex<maxColIndex;colIndex++) { //iterating from first to last column of first row to store column header names
				Cell cell = firstRow.getCell(colIndex); //get the cell value
				indexMap.put(cell.getStringCellValue().toString(), cell.getColumnIndex()); // mapping column names with it's corresponding column index
				
			}
			
			int intKey = 0;
			multimapData.clear();
			for(int i=0;i<lastRowNum;i++) {
				String key = String.valueOf(intKey);
				
				String value1 = formatter.formatCellValue(sheet.getRow(i).getCell(indexMap.get(colName1)));
				String value2 = formatter.formatCellValue(sheet.getRow(i).getCell(indexMap.get(colName2)));
				String value3 = formatter.formatCellValue(sheet.getRow(i).getCell(indexMap.get(colName3)));
				String value4 = formatter.formatCellValue(sheet.getRow(i).getCell(indexMap.get(colName4)));
				
				multimapData.put(key, new ArrayList<String>());
				multimapData.get(key).add(value1);
				multimapData.get(key).add(value2);
				multimapData.get(key).add(value3);
				multimapData.get(key).add(value4);
				
				intKey++;
			}
			fis.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return multimapData ;		
	}
	
}
