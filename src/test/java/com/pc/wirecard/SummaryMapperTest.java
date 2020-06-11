package com.pc.wirecard;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pc.wirecard.core.ITransform;
import com.pc.wirecard.core.internalreport.sheet.SheetOne;
import com.pc.wirecard.core.internalreport.sheet.SummarySheet;
import com.pc.wirecard.model.MerchantNameAndDate;
import com.pc.wirecard.model.internalreport.SheetOneInfo;
import com.pc.wirecard.model.internalreport.SummaryInfo;
import com.pc.wirecard.model.poiji.Sheet1CellInfoTest;
import com.pc.wirecard.util.PoiUtils;
import com.pc.wirecard.util.WirecardUtils;
import com.poiji.bind.Poiji;

@SpringBootTest
public class SummaryMapperTest {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ITransform<SummaryInfo, SheetOneInfo> summarySheetMapper;
	
	@Test
	public void test() throws IOException {
		
		List<Sheet1CellInfoTest> list = Poiji.fromExcel(new File("sample\\InternalPUC-CHARLES_KEITHDTReport2019-06-12.xls"), Sheet1CellInfoTest.class);
		
		list.forEach(item -> {
			if (item.getCcy().equals("PHP")) {
				System.out.println("O Gross Amount: "+item.getoGrossAmt()); //TODO: Poiji's having problem with negative values that are in parenthesis
				System.out.println("SGD Amount: "+item.getSgdAmount());
			}
		});
				
		
		List<SheetOneInfo> cellInfoList = list.stream().map(item -> modelMapper.map(item, SheetOneInfo.class)).collect(Collectors.toList());  
		 
		Path destination = Paths.get("C:\\Wirecard Reports\\temp\\test.xls");
		
		MerchantNameAndDate md = new MerchantNameAndDate();
		md.setMerchantName("Test");
		md.setDate("01-Jan-2020");
		Workbook workbook = new HSSFWorkbook();
		
		SheetOne sheetOne = new SheetOne(workbook, md);
		SummarySheet sheetTwo = new SummarySheet(workbook, md);		
		List<SummaryInfo> summaryCellInfoList = summarySheetMapper.transform(cellInfoList);
		
		sheetOne.createSheet(cellInfoList);
		sheetTwo.createSheet(summaryCellInfoList);
		
		PoiUtils.autoSizeColumns(workbook);
		
		WirecardUtils.writeToExcel(workbook, destination);
		
		assertTrue(Files.exists(destination));
		
	}
	
}
