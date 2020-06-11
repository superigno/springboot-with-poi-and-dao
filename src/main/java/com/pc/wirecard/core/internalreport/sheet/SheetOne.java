package com.pc.wirecard.core.internalreport.sheet;

import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_ACQREFNUM;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_AUTHCODE;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_BATCHSOCNBR;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_CARDNBR;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_CARDTYPE;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_CCY;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_DATAENTRYMODE;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_DESCRIPTION;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_EXCEPTION_SGD_AMOUNT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_GAPOSMODE;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_LOGICMODULE;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_MCC;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_MERCHANTID;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_MERCHANTNAME;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_ONUSOFFUSFLAG;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_ORG;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_O_COMAMT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_O_GROSSAMT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_O_NETAMT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_O_OTHAMT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_O_VATAMT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_POSTDATE;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_PRODUCT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_QUALINDICATOR;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_REFBATCHSRCE;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_ROCTEXT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_SGD_AMOUNT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_SGD_PAYMENT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_SUBMEDIA;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_SUBMITAMT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_SUBMITCCY;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_TERMINALID;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_TRANSAMT;
import static com.pc.wirecard.constant.WirecardConstants.IR_HEADER_TRANSDATE;
import static com.pc.wirecard.util.PoiUtils.populateCell;
import static com.pc.wirecard.util.WirecardUtils.isZero;
import static com.pc.wirecard.util.WirecardUtils.roundUpTwoDecimalPlaces;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.pc.wirecard.constant.PoiConstants;
import com.pc.wirecard.core.ISheet;
import com.pc.wirecard.model.MerchantNameAndDate;
import com.pc.wirecard.model.internalreport.SheetOneInfo;

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

		final String[] headers = { IR_HEADER_ORG, IR_HEADER_MERCHANTID, IR_HEADER_MERCHANTNAME, IR_HEADER_TERMINALID, IR_HEADER_BATCHSOCNBR, IR_HEADER_REFBATCHSRCE,
				IR_HEADER_CARDNBR, IR_HEADER_TRANSDATE, IR_HEADER_CCY, IR_HEADER_TRANSAMT, IR_HEADER_AUTHCODE, IR_HEADER_CARDTYPE, IR_HEADER_ONUSOFFUSFLAG, IR_HEADER_PRODUCT,
				IR_HEADER_ACQREFNUM, IR_HEADER_DESCRIPTION, IR_HEADER_POSTDATE, IR_HEADER_SUBMITCCY, IR_HEADER_SUBMITAMT, IR_HEADER_SUBMEDIA, IR_HEADER_LOGICMODULE,
				IR_HEADER_QUALINDICATOR, IR_HEADER_DATAENTRYMODE, IR_HEADER_MCC, IR_HEADER_GAPOSMODE, IR_HEADER_O_GROSSAMT, IR_HEADER_O_COMAMT, IR_HEADER_O_VATAMT, IR_HEADER_O_OTHAMT,
				IR_HEADER_O_NETAMT, IR_HEADER_ROCTEXT, IR_HEADER_SGD_AMOUNT, IR_HEADER_SGD_PAYMENT, IR_HEADER_EXCEPTION_SGD_AMOUNT };

		final CellStyle headerStyle = workbook.createCellStyle();
		final Font headerFont = workbook.createFont();
		headerFont.setFontName(PoiConstants.Font.NAME);
		headerFont.setFontHeightInPoints(PoiConstants.Font.SIZE_9);
		headerFont.setBold(true);
		headerStyle.setFont(headerFont);
		headerStyle.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.TEXT));

		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(headerStyle);
		}

	}

	private void populateCells(final Sheet sheet, final List<SheetOneInfo> list) {
		final CellStyle cellStyleGeneral = workbook.createCellStyle();
		final CellStyle cellStyleDate = workbook.createCellStyle();
		final CellStyle cellStyleCurrency = workbook.createCellStyle();
		final CellStyle cellStyleText = workbook.createCellStyle();
		
		final Font font = workbook.createFont();
		font.setFontName(PoiConstants.Font.NAME);
		font.setFontHeightInPoints(PoiConstants.Font.SIZE_9);
				
		cellStyleDate.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.DATE));
		cellStyleCurrency.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.CURRENCY));
		cellStyleText.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.TEXT));
		cellStyleGeneral.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.GENERAL));
		
		cellStyleGeneral.setFont(font);
		cellStyleDate.setFont(font);
		cellStyleCurrency.setFont(font);
		cellStyleText.setFont(font);		
		
		int rowNum = sheet.getLastRowNum();
		for (SheetOneInfo info : list) {
			Row row = sheet.createRow(++rowNum);

			populateCell(row, 0, info.getOrg(), cellStyleText);			
			populateCell(row, 1, info.getMerchantId(), cellStyleText);			
			populateCell(row, 2, info.getMerchantName(), cellStyleText);			
			populateCell(row, 3, info.getTerminalId(), cellStyleText);			
			populateCell(row, 4, info.getBatchSocNbr(), cellStyleText);
			populateCell(row, 5, info.getRefBatchSrce(), cellStyleText);
			populateCell(row, 6, info.getCardNbr(), cellStyleText);			
			populateCell(row, 7, info.getTransDate(), cellStyleDate);
			populateCell(row, 8, info.getCcy(), cellStyleText);			
			populateCell(row, 9, info.getTransAmt().doubleValue(), cellStyleCurrency);			
			populateCell(row, 10, info.getAuthCode(), cellStyleText);			
			populateCell(row, 11, info.getCardType(), cellStyleText);			
			populateCell(row, 12, info.getOnUsOffUsFlag(), cellStyleText);			
			populateCell(row, 13, info.getProduct(), cellStyleText);			
			populateCell(row, 14, info.getAcqRefNum(), cellStyleText);			
			populateCell(row, 15, info.getDescription(), cellStyleText);			
			populateCell(row, 16, info.getPostDate(), cellStyleDate);			
			populateCell(row, 17, info.getSubmitCcy(), cellStyleText);			
			populateCell(row, 18, info.getSubmitAmt().doubleValue(), cellStyleCurrency);			
			populateCell(row, 19, info.getSubMedia(), cellStyleText);			
			populateCell(row, 20, info.getLogicModule(), cellStyleText);			
			populateCell(row, 21, info.getQualIndicator(), cellStyleText);			
			populateCell(row, 22, info.getDataEntryMode(), cellStyleText);			
			populateCell(row, 23, info.getMcc(), cellStyleText);			
			populateCell(row, 24, info.getGapOsMode(), cellStyleText);			
			populateCell(row, 25, info.getoGrossAmt().doubleValue(), cellStyleCurrency);			
			populateCell(row, 26, info.getoComAmt().doubleValue(), cellStyleCurrency);			
			populateCell(row, 27, info.getoVatAmt().doubleValue(), cellStyleCurrency);			
			populateCell(row, 28, info.getoOthAmt().doubleValue(), cellStyleCurrency);			
			populateCell(row, 29, info.getoNetAmt().doubleValue(), cellStyleCurrency);			
			populateCell(row, 30, info.getRocText(), cellStyleText);
			
			if (isZero(info.getSgdAmount())) {
				populateCell(row, 31, "", cellStyleGeneral);				
			} else {
				populateCell(row, 31, roundUpTwoDecimalPlaces(info.getSgdAmount()).doubleValue(), cellStyleGeneral);
			}
			
			if (isZero(info.getSgdPayment())) {
				populateCell(row, 32, "", cellStyleGeneral);				
			} else {
				populateCell(row, 32, roundUpTwoDecimalPlaces(info.getSgdPayment()).doubleValue(), cellStyleGeneral);
			}
			
			if (isZero(info.getExceptionSgdAmount())) {
				populateCell(row, 33, "", cellStyleGeneral);				
			} else {
				populateCell(row, 33, roundUpTwoDecimalPlaces(info.getExceptionSgdAmount()).doubleValue(), cellStyleGeneral);
			}
			
		}

	}	

}
