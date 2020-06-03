package com.pc.wirecard;

import java.io.File;
import java.io.IOException;
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
import com.pc.wirecard.core.internalreport.sheet.SummarySheet;
import com.pc.wirecard.model.MerchantNameAndDate;
import com.pc.wirecard.model.internalreport.SheetOneInfo;
import com.pc.wirecard.model.internalreport.SummaryInfo;
import com.pc.wirecard.model.poiji.Sheet1CellInfoTest;
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
		List<SheetOneInfo> cellInfoList = list.stream().map(item -> modelMapper.map(item, SheetOneInfo.class)).collect(Collectors.toList());
		Path destination = Paths.get("C:\\Wirecard Reports\\temp\\summary2.xls");
		
		MerchantNameAndDate md = new MerchantNameAndDate();
		md.setMerchantName("Test");
		md.setDate("01-Jan-2020");
		Workbook workbook = new HSSFWorkbook();
		SummarySheet sheetTwo = new SummarySheet(workbook, md);
		
		/*
		final List<String> optOutProducts = Arrays.asList(new String[] {"05","06","07"});		
		final List<Sheet1CellInfo> optOutList = cellInfoList.stream().filter(info -> "SGD".equals(info.getCcy()) && optOutProducts.contains(info.getProduct())).collect(Collectors.toList());
		final double totalSettledByCitiToPc = optOutList.stream().mapToDouble(info -> info.getoNetAmt().doubleValue()).sum();
		
		optOutList.forEach(System.out::println);
		*/
		
		List<SummaryInfo> summaryCellInfoList = summarySheetMapper.transform(cellInfoList);
		sheetTwo.createSheet(summaryCellInfoList);
		
		WirecardUtils.writeToExcel(workbook, destination);
		
	}
	
}
