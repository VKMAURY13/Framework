package com.comcast.CRM.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetname,int rownum, int cellnum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("./TestData/TestCaseOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		
		return sh.getRow(rownum).getCell(cellnum).getStringCellValue();
	}
	
	public void setDataIntoExcel(String sheetname,int rownum, int cellnum, String value) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("./TestData/TestCaseOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row row = sh.getRow(rownum);
		Cell cel = row.createCell(cellnum);
		cel.setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream("./TestData/TestCaseOrg.xlsx");
		wb.write(fos);
		
		wb.close();
	}
	
	public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException
	{

		FileInputStream fis = new FileInputStream("./TestData/TestCaseOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		int rowcount = sh.getLastRowNum();
		
		return rowcount;
	}
	
	public double getDataFromExcelNumeric(String sheetname,int rownum, int cellnum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("./TestData/TestCaseOrg.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		
		return sh.getRow(rownum).getCell(cellnum).getNumericCellValue();
	}
	

}
