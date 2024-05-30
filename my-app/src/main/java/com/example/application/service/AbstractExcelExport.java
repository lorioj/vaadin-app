package com.example.application.service;

import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;

public abstract class AbstractExcelExport {

	protected static final int CHAR_WIDTH = 256;

	protected void createCell(Sheet sheet, int rowIdx, int col, String value) {
		createCell(sheet, rowIdx, col, value, null);
	}

	protected void createCell(Sheet sheet, int rowIdx, int col, String value, CellStyle cellStyle) {
		createCell(sheet, rowIdx, col, value, cellStyle, null);
	}

	protected void createCell(Sheet sheet, int rowIdx, int col, String value, CellStyle cellStyle, Map<String, Object> properties) {
		Cell cell = createCell(sheet, rowIdx, col, cellStyle, null);
		cell.setCellValue(value);
	}

	protected Row createRow(Sheet sheet, int rowIdx) {
		Row row = sheet.getRow(rowIdx);
		if (row == null) {
			row = sheet.createRow(rowIdx);
		}
		return row;
	}

	protected Cell createCell(Sheet sheet, int rowIdx, int col, CellStyle cellStyle, Map<String, Object> properties) {
		Row row = createRow(sheet, rowIdx);
		Cell cell = row.createCell(col);
		cell.setCellStyle(cellStyle);
		if (properties != null) {
			CellUtil.setCellStyleProperties(cell, properties);
		}
		return cell;
	}
}
