package com.pc.wirecard.core.internalreport;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pc.wirecard.core.IReport;
import com.pc.wirecard.core.ISheet;
import com.pc.wirecard.core.ITransform;
import com.pc.wirecard.core.SourceFile;
import com.pc.wirecard.core.internalreport.sheet.SheetOne;
import com.pc.wirecard.core.internalreport.sheet.SummarySheet;
import com.pc.wirecard.model.MerchantNameAndDate;
import com.pc.wirecard.model.internalreport.SheetOneInfo;
import com.pc.wirecard.model.internalreport.SummaryInfo;
import com.pc.wirecard.model.poiji.RoctextInfo;
import com.pc.wirecard.util.PoiUtils;
import com.pc.wirecard.util.WirecardUtils;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Component
public class InternalReport implements IReport<RoctextInfo> {
	
	@Autowired
	private ITransform<SheetOneInfo, RoctextInfo> internalSheetOneTransformer;
	
	@Autowired
	private ITransform<SummaryInfo, SheetOneInfo> summarySheetTransfomer;
	
	private MerchantNameAndDate md;
	
	private Workbook workbook;
	
	public Workbook generateReport(final SourceFile<RoctextInfo> sourceFile) {
		md = WirecardUtils.extractMerchantNameAndDateFromFilename(sourceFile.getFilename());
		workbook = new HSSFWorkbook();
		
		final ISheet<SheetOneInfo> sheetOne = new SheetOne(workbook, md);
		final ISheet<SummaryInfo> sheetTwo = new SummarySheet(workbook, md);
		
		final List<SheetOneInfo> cellInfoList = internalSheetOneTransformer.transform(sourceFile.asList());
		final List<SummaryInfo> summaryCellInfoList = summarySheetTransfomer.transform(cellInfoList);
		
		sheetOne.createSheet(cellInfoList);
		sheetTwo.createSheet(summaryCellInfoList);
		
		PoiUtils.autoSizeColumns(workbook);
		
		return workbook;
	}

	@Override
	public void saveToFile(Path destinationDir) throws IOException {
		final String reportFilename = "Internal"+md.getMerchantName()+"Report"+md.getFormattedDate()+".xls";
		//final Path datedDirPath = FileUtils.getDatedPath(destinationDir, md.getFormattedDate());
		WirecardUtils.writeToExcel(workbook, destinationDir.resolve(reportFilename));		
	}

}
