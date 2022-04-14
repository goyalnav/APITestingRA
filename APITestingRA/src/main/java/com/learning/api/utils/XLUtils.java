package com.learning.api.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static XSSFCellStyle style;

	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		int rowcount;
		XLUtils.fi = new FileInputStream(xlfile);
		XLUtils.wb = new XSSFWorkbook(XLUtils.fi);
		XLUtils.ws = XLUtils.wb.getSheet(xlsheet);
		rowcount = XLUtils.ws.getLastRowNum();
		XLUtils.wb.close();
		XLUtils.fi.close();
		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		int cellcount;
		XLUtils.fi = new FileInputStream(xlfile);
		XLUtils.wb = new XSSFWorkbook(XLUtils.fi);
		XLUtils.ws = XLUtils.wb.getSheet(xlsheet);
		XLUtils.row = XLUtils.ws.getRow(rownum);
		cellcount = XLUtils.row.getLastCellNum();
		XLUtils.wb.close();
		XLUtils.fi.close();
		return cellcount;

	}

	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		String data;
		XLUtils.fi = new FileInputStream(xlfile);
		XLUtils.wb = new XSSFWorkbook(XLUtils.fi);
		XLUtils.ws = XLUtils.wb.getSheet(xlsheet);
		XLUtils.row = XLUtils.ws.getRow(rownum);
		XLUtils.cell = XLUtils.row.getCell(colnum);
		try {
			data = XLUtils.cell.getStringCellValue();
			return data;

		} catch (Exception e) {
			data = "";
			return data;
		}
	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {

		XLUtils.fi = new FileInputStream(xlfile);
		XLUtils.wb = new XSSFWorkbook(XLUtils.fi);
		XLUtils.ws = XLUtils.wb.getSheet(xlsheet);
		XLUtils.row = XLUtils.ws.getRow(rownum);
		XLUtils.cell = XLUtils.row.createCell(colnum);
		XLUtils.cell.setCellValue(data);

		XLUtils.fo = new FileOutputStream(xlfile);
		XLUtils.wb.write(XLUtils.fo);
		XLUtils.fi.close();
		XLUtils.fo.close();

	}

	public static void fillGreenColour(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		XLUtils.fi = new FileInputStream(xlfile);
		XLUtils.wb = new XSSFWorkbook(XLUtils.fi);
		XLUtils.ws = XLUtils.wb.getSheet(xlsheet);
		XLUtils.row = XLUtils.ws.getRow(rownum);
		XLUtils.cell = XLUtils.row.getCell(colnum);
		XLUtils.style = XLUtils.wb.createCellStyle();
		XLUtils.style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		XLUtils.style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XLUtils.cell.setCellStyle(XLUtils.style);

		XLUtils.fo = new FileOutputStream(xlfile);
		XLUtils.wb.write(XLUtils.fo);
		XLUtils.wb.close();
		XLUtils.fi.close();
		XLUtils.fo.close();
	}

	public static void fillRedColour(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		XLUtils.fi = new FileInputStream(xlfile);
		XLUtils.wb = new XSSFWorkbook(XLUtils.fi);
		XLUtils.ws = XLUtils.wb.getSheet(xlsheet);
		XLUtils.row = XLUtils.ws.getRow(rownum);
		XLUtils.cell = XLUtils.row.getCell(colnum);
		XLUtils.style = XLUtils.wb.createCellStyle();
		XLUtils.style.setFillForegroundColor(IndexedColors.RED.getIndex());
		XLUtils.style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XLUtils.cell.setCellStyle(XLUtils.style);

		XLUtils.fo = new FileOutputStream(xlfile);
		XLUtils.wb.write(XLUtils.fo);
		XLUtils.wb.close();
		XLUtils.fi.close();
		XLUtils.fo.close();

	}

}
