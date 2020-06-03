package com.pc.wirecard.service.wirecard;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.wirecard.constant.WirecardConstants;
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
public class InternalReportService implements IReportService {
	
	@Autowired
	private IReport<RoctextInfo> internalReport;
	
	@Override
	public void convertToReport(Path source, Path destinationDir) throws IOException {
		final SourceFile<RoctextInfo> sourceFile = new SourceFile<>(source, RoctextInfo.class);
		
		WirecardUtils.validateFilename(sourceFile.getFilename());
		
		if (ifHasDccWithEmptyBaseAmount(sourceFile)) {
			throw new IOException("Has DCC with empty SGD Amount");
		} else {
			internalReport.generateReport(sourceFile);
			internalReport.saveToFile(destinationDir);
		}
		
	}
	
	private boolean ifHasDccWithEmptyBaseAmount(final SourceFile<RoctextInfo> sourceFile) {
		final long count = sourceFile.asList().stream().filter(item -> (!WirecardConstants.CURRENCY_BASE.equals(item.getCcy()) && (item.getBaseAmt() == null || WirecardUtils.isZero(item.getBaseAmt())))).count();
		return count > 0;
	}

}
