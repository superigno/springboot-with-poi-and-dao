package com.pc.wirecard.core.internalreport.sheet;

import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_HEADER_CITI_MDR_CHARGE;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_HEADER_DESCRIPTION;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_HEADER_GROSS_AMT_FROM_CITI;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_HEADER_MDR_PAY_BY_MERCHANT;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_HEADER_MERCHANT_COMMISSION;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_HEADER_SETTLED_BY_CITI_TO_PC;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_HEADER_SETTLEMENT_AMOUNT_TO_MERCHANT;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_HEADER_TRANSACTION_AMOUNT_MATCH_IN_DB;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_HEADER_TRANSACTION_AMOUNT_SGD;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_DCC_HIT_RATE;
import static com.pc.wirecard.constant.WirecardConstants.ORDER_SUMMARY_TOTALS;
import static com.pc.wirecard.constant.WirecardConstants.ORDER_SUMMARY_MAJOR_CURRENCY;
import static com.pc.wirecard.constant.WirecardConstants.ORDER_SUMMARY_MINOR_CURRENCY;
import static com.pc.wirecard.util.PoiUtils.populateCell;
import static com.pc.wirecard.util.WirecardUtils.isZero;

import java.util.List;
import java.util.stream.Collectors;

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
import com.pc.wirecard.model.internalreport.SummaryInfo;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class SummarySheet implements ISheet<SummaryInfo> {

	private Workbook workbook;
	private MerchantNameAndDate md;

	public SummarySheet(final Workbook workbook) {
		this.workbook = workbook;
	}

	public SummarySheet(final Workbook workbook, final MerchantNameAndDate md) {
		this.workbook = workbook;
		this.md = md;
	}

	@Override
	public void createSheet(List<SummaryInfo> list) {
		final Sheet sheet = this.workbook.createSheet("Summary");
		populateHeaders(sheet);

		List<SummaryInfo> firstList = list.stream().filter(item -> (item.getOrder() == ORDER_SUMMARY_MAJOR_CURRENCY
				|| item.getOrder() == ORDER_SUMMARY_MINOR_CURRENCY)).collect(Collectors.toList());
		populateTopCells(sheet, firstList);

		List<SummaryInfo> secondList = list.stream().filter(item -> (item.getOrder() == ORDER_SUMMARY_TOTALS))
				.collect(Collectors.toList());
		populateBottomCells(sheet, secondList);

	}

	private void populateHeaders(final Sheet sheet) {

		final String[] headers = { IR_SUMMARY_HEADER_DESCRIPTION, IR_SUMMARY_HEADER_GROSS_AMT_FROM_CITI,
				IR_SUMMARY_HEADER_SETTLED_BY_CITI_TO_PC, IR_SUMMARY_HEADER_CITI_MDR_CHARGE,
				IR_SUMMARY_HEADER_TRANSACTION_AMOUNT_MATCH_IN_DB, IR_SUMMARY_HEADER_TRANSACTION_AMOUNT_SGD,
				IR_SUMMARY_HEADER_SETTLEMENT_AMOUNT_TO_MERCHANT, IR_SUMMARY_HEADER_MDR_PAY_BY_MERCHANT,
				IR_SUMMARY_HEADER_MERCHANT_COMMISSION };

		final CellStyle headerStyle = workbook.createCellStyle();
		final Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(headerFont);
		headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.GENERAL));

		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i].replace("{MERCHANT_NAME}", md.getMerchantName().toUpperCase()));
			cell.setCellStyle(headerStyle);
		}

	}

	private void populateTopCells(final Sheet sheet, final List<SummaryInfo> list) {
		final CellStyle cellStyleGeneral = workbook.createCellStyle();
		final CellStyle cellStyleNumber = workbook.createCellStyle();
		
		final Font font = workbook.createFont();
		font.setFontName(PoiConstants.Font.NAME);
		font.setFontHeightInPoints(PoiConstants.Font.SIZE_11);
		
		cellStyleGeneral.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.GENERAL));
		cellStyleNumber.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.NUMBER));
		cellStyleGeneral.setFont(font);
		cellStyleNumber.setFont(font);

		int rowNum = sheet.getLastRowNum();
		for (SummaryInfo info : list) {
			Row row = sheet.createRow(++rowNum);
			populateCell(row, 0, info.getDescription(), cellStyleGeneral);
			populateCell(row, 1, info.getGrossAmountFromCiti().doubleValue(), cellStyleNumber);
			populateCell(row, 2, info.getSettledByCitiToPc().doubleValue(), cellStyleNumber);
			populateCell(row, 3, info.getCitiMdrCharge().doubleValue(), cellStyleNumber);
			populateCell(row, 4, info.getTransactionAmountMatch().doubleValue(), cellStyleNumber);
			populateCell(row, 5, info.getTransactionAmountSgd().doubleValue(), cellStyleNumber);
			populateCell(row, 6, info.getSettlementAmountToMerchant().doubleValue(), cellStyleNumber);
			populateCell(row, 7, info.getMdrPayByMerchant().doubleValue(), cellStyleNumber);
			populateCell(row, 8, info.getMerchantCommission().doubleValue(), cellStyleNumber);
		}

	}

	private void populateBottomCells(final Sheet sheet, final List<SummaryInfo> list) {
		final CellStyle headerStyle = workbook.createCellStyle();
		final CellStyle cellStyleNumber = workbook.createCellStyle();
		final CellStyle cellStylePercentage = workbook.createCellStyle();
		
		final Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		
		final Font font = workbook.createFont();
		font.setFontName(PoiConstants.Font.NAME);
		font.setFontHeightInPoints(PoiConstants.Font.SIZE_11);
		
		cellStyleNumber.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.NUMBER));
		cellStylePercentage.setDataFormat(workbook.createDataFormat().getFormat(PoiConstants.DataFormat.PERCENTAGE));
		headerStyle.setFont(headerFont);
		cellStyleNumber.setFont(font);
		cellStylePercentage.setFont(font);
		
		int rowNum = sheet.getLastRowNum() + 1;
		for (SummaryInfo info : list) {

			Row row = sheet.createRow(++rowNum);

			final String description = info.getDescription() != null
					? info.getDescription().replace("{MERCHANT_NAME}", md.getMerchantName().toUpperCase())
					: info.getDescription();
			populateCell(row, 0, description, headerStyle);

			if (isZero(info.getSettledByCitiToPc())) {
				populateCell(row, 2, "");
			} else if (IR_SUMMARY_SUBHEADER_DCC_HIT_RATE.equals(info.getDescription())) {
				populateCell(row, 2, info.getSettledByCitiToPc().doubleValue(), cellStylePercentage);
			} else {
				populateCell(row, 2, info.getSettledByCitiToPc().doubleValue(), cellStyleNumber);
			}

			if (isZero(info.getTransactionAmountSgd())) {
				populateCell(row, 5, "");
			} else {
				populateCell(row, 5, info.getTransactionAmountSgd().doubleValue(), cellStyleNumber);
			}

			if (isZero(info.getSettlementAmountToMerchant())) {
				populateCell(row, 6, "");
			} else {
				populateCell(row, 6, info.getSettlementAmountToMerchant().doubleValue(), cellStyleNumber);
			}

			if (isZero(info.getMdrPayByMerchant())) {
				populateCell(row, 7, "");
			} else {
				populateCell(row, 7, info.getMdrPayByMerchant().doubleValue(), cellStyleNumber);
			}

		}

	}

}
