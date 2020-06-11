package com.pc.wirecard.core.externalreport.sheet;

import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_AUTHORISATION_NUMBER;
import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_CARD_NO;
import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_COMMISSION;
import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_CREDIT_AMOUNT;
import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_DATE;
import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_MDR_RATE;
import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_MID;
import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_REMARK;
import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_RRN_OCENIA;
import static com.pc.wirecard.constant.WirecardConstants.ER_HEADER_TRANSACTION_SALES;

import java.util.List;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.pc.wirecard.constant.PoiConstants;
import com.pc.wirecard.core.ISheet;
import com.pc.wirecard.model.MerchantNameAndDate;
import com.pc.wirecard.model.externalreport.SheetOneInfo;
import static com.pc.wirecard.util.PoiUtils.*;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class SheetOne implements ISheet<SheetOneInfo> {

	private Workbook workbook;
	private MerchantNameAndDate md;
	
	public SheetOne(final Workbook workbook) {
		this.workbook = workbook;
	}
	
	public SheetOne(final Workbook workbook, final MerchantNameAndDate md) {
		this.workbook = workbook;
		this.md = md;
	}
	
	public void createSheet(final List<SheetOneInfo> list) {
		final Sheet sheet = this.workbook.createSheet(md.getDate());
		populateHeaders(sheet);
		populateCells(sheet, list);
	}

	private void populateHeaders(final Sheet sheet) {

		final String[] headers = { ER_HEADER_MID, ER_HEADER_DATE, ER_HEADER_CARD_NO, ER_HEADER_AUTHORISATION_NUMBER,
				ER_HEADER_TRANSACTION_SALES, ER_HEADER_MDR_RATE, ER_HEADER_COMMISSION, ER_HEADER_CREDIT_AMOUNT,
				ER_HEADER_REMARK, ER_HEADER_RRN_OCENIA };

		final CellStyle headerStyle = workbook.createCellStyle();
		
		final Font headerFont = workbook.createFont();
		headerFont.setFontName(PoiConstants.Font.NAME);
		headerFont.setFontHeightInPoints(PoiConstants.Font.SIZE_9);

		headerStyle.setFont(headerFont);
		headerStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setDataFormat(workbook.createDataFormat().getFormat(BuiltinFormats.getBuiltinFormat(1)));

		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(headerStyle);
		}

	}

	private void populateCells(final Sheet sheet, final List<SheetOneInfo> list) {
		final CellStyle cellStyleDate = workbook.createCellStyle();
		final CellStyle cellStyleCurrency = workbook.createCellStyle();
		final CellStyle cellStyleText = workbook.createCellStyle();
		
		final Font font = workbook.createFont();
		font.setFontName(PoiConstants.Font.NAME);
		font.setFontHeightInPoints(PoiConstants.Font.SIZE_9);
		
		cellStyleDate.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.DATE));
		cellStyleCurrency.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.CURRENCY));
		cellStyleText.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.TEXT));
		cellStyleDate.setFont(font);
		cellStyleCurrency.setFont(font);
		cellStyleText.setFont(font);
				
		int rowNum = sheet.getLastRowNum();
		for (SheetOneInfo info : list) {
			Row row = sheet.createRow(++rowNum);			
			populateCell(row, 0, info.getMid(), cellStyleText);
			populateCell(row, 1, info.getDate(), cellStyleDate);
			populateCell(row, 2, info.getCardNumber(), cellStyleText);
			populateCell(row, 3, info.getAuthorisationNumber(), cellStyleText);
			populateCell(row, 4, info.getTransactionSales().doubleValue(), cellStyleCurrency);
			populateCell(row, 5, info.getMdrRate().doubleValue(), cellStyleCurrency);
			populateCell(row, 6, info.getCommission().doubleValue(), cellStyleCurrency);
			populateCell(row, 7, info.getCreditAmount().doubleValue(), cellStyleCurrency);
			populateCell(row, 8, info.getRemark(), cellStyleText);
			populateCell(row, 9, info.getRrn(), cellStyleText);
		}
	}	

}
