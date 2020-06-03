package com.pc.wirecard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pc.wirecard.service.IReportService;

@SpringBootTest
class InternalReportTest {

	@Autowired
	IReportService internalReportService;
	
	@Test
	void test() {
		
		final Path source = Paths.get("sample\\PUC-Vendermac-31-Jan-2020.xls");
		final Path destination = Paths.get("C:\\Wirecard Reports\\temp\\internal");
		
		assertFalse(Files.exists(destination));
		
		try {
			internalReportService.convertToReport(source, destination);
			assertTrue(Files.exists(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
