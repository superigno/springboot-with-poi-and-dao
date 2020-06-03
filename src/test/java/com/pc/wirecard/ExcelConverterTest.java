package com.pc.wirecard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pc.wirecard.model.poiji.RoctextInfo;
import com.poiji.bind.Poiji;

@SpringBootTest
class ExcelConverterTest {
	
	@Test
	void testExcelToObject() {
		List<RoctextInfo> list = Poiji.fromExcel(new File("sample\\PUC-Vendermac-31-Jan-2020.xls"), RoctextInfo.class);
		System.out.println("List size: "+list.size());
		assertTrue(list.size() > 0);		
		RoctextInfo info = list.get(0);
		assertEquals(info.getOrg(), "602");
		
		list.stream().forEach(i -> System.out.println(i.getTransAmt()));
		
	}
	

}
