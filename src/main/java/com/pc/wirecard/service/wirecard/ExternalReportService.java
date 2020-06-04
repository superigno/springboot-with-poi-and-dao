package com.pc.wirecard.service.wirecard;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.wirecard.core.IReport;
import com.pc.wirecard.core.SourceFile;
import com.pc.wirecard.model.poiji.RoctextInfo;
import com.pc.wirecard.service.IReportService;
import com.pc.wirecard.util.WirecardUtils;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Service
public class ExternalReportService implements IReportService {

	@Autowired
	private IReport<RoctextInfo> externalReport;
	
	@Override
	public void convertToReport(Path source, Path destinationDir) throws IOException {
		final SourceFile<RoctextInfo> sourceFile = new SourceFile<>(source, RoctextInfo.class);		
		WirecardUtils.validateFilename(sourceFile.getFilename());
		WirecardUtils.validateDccWithEmptyBaseAmount(sourceFile);
		externalReport.generateReport(sourceFile);
		externalReport.saveToFile(destinationDir);
	}

}
