package com.pc.wirecard.core.externalreport;

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
import com.pc.wirecard.core.externalreport.sheet.SheetOne;
import com.pc.wirecard.model.MerchantNameAndDate;
import com.pc.wirecard.model.externalreport.SheetOneInfo;
import com.pc.wirecard.model.poiji.RoctextInfo;
import com.pc.wirecard.util.FileUtils;
import com.pc.wirecard.util.WirecardUtils;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Component
public class ExternalReport implements IReport<RoctextInfo> {

	@Autowired
	private ITransform<SheetOneInfo, RoctextInfo> externalSheetOneTransformer;
	
	private MerchantNameAndDate md;
	
	private Workbook workbook;
	
	@Override
	public Workbook generateReport(final SourceFile<RoctextInfo> sourceFile) {
		md = WirecardUtils.extractMerchantNameAndDateFromFilename(sourceFile.getFilename());
		workbook = new HSSFWorkbook();
		final ISheet<SheetOneInfo> sheetOne = new SheetOne(workbook, md);
		final List<SheetOneInfo> cellInfoList = externalSheetOneTransformer.transform(sourceFile.asList());
		sheetOne.createSheet(cellInfoList);
		return workbook;
	}
	
	@Override
	public void saveToFile(Path destinationDir) throws IOException {
		final String reportFilename = "External"+md.getMerchantName()+"Report"+md.getFormattedDate()+".xls";
		final Path datedDirPath = FileUtils.getDatedPath(destinationDir, md.getFormattedDate());
		WirecardUtils.writeToExcel(workbook, datedDirPath.resolve(reportFilename));
	}	

}
