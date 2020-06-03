package com.pc.wirecard.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

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
	
}
