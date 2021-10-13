package com.crm.vtiger.genericUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtility {
	/*
	 * author @ sohan
	 */
	/**
	 * This method used to read data from excel by specifing sheetname, row number and cell number
	 * @param sheetname
	 * @param rownum
	 * 
	 * @param cellnum
	 * @return
	 * @throws Throwable
	 * @throws IOException
	 */
	public String getExcelData(String sheetname, int rownum, int cellnum) throws Throwable, IOException {
		FileInputStream fis = new FileInputStream(IPathConstants.EXCELPATH);
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		
		return data;
	}
	
	public Object[][] getExcelData(String sheetname) throws Throwable{
		FileInputStream file = new FileInputStream(IPathConstants.EXCELPATH);
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet(sheetname);
		int lastRow = sheet.getLastRowNum();
		short lastCell = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[lastRow][lastCell];
		for(int i=0; i<lastRow; i++) {
			for(int j=0; j<lastCell; j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).getStringCellValue();
			}
		}
		return data;
		
	}
	/**
	 * this method used to write data inside the excel sheet
	 * @param sheetname
	 * @param rownum
	 * @param column
	 * @param value
	 * @throws Throwable
	 */
	public void writeExcelData(String sheetname, int rownum, int column, String value) throws Throwable {
		FileInputStream file = new FileInputStream(IPathConstants.EXCELPATH);
		Workbook workbook = WorkbookFactory.create(file);
		workbook.createSheet(sheetname).createRow(rownum).createCell(column).setCellValue(value);
		FileOutputStream writeFile = new FileOutputStream(IPathConstants.EXCELPATH);
		workbook.write(writeFile);
	}
}
