package com.pc.wirecard.util;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author gino.q
 * @date June 1, 2020
 * 
 */
public class PoiUtils {

	public static void populateCell(final Row row, final int cellNo, final String value, final CellStyle cellStyle) {
		final Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
	}

	public static void populateCell(final Row row, final int cellNo, final double value, final CellStyle cellStyle) {
		final Cell cell = row.createCell(cellNo);
		cell.setCellValue(value);
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
	}

	public static void populateCell(final Row row, final int cellNo, final String value) {
		populateCell(row, cellNo, value, null);
	}

	public static void populateCell(final Row row, final int cellNo, final double value) {
		populateCell(row, cellNo, value, null);
	}

	public static void autoSizeColumns(Workbook workbook) {
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			if (sheet.getPhysicalNumberOfRows() > 0) {
				Row row = sheet.getRow(sheet.getFirstRowNum());
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int columnIndex = cell.getColumnIndex();
					sheet.autoSizeColumn(columnIndex);
					//If slow, suggest to use fixed width
					//int currentColumnWidth = sheet.getColumnWidth(columnIndex);
					//int width = currentColumnWidth + 2500;					
					//sheet.setColumnWidth(columnIndex, width);
				}
			}
		}
	}

}
