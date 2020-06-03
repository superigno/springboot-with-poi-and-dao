package com.pc.wirecard.service;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public interface IReportService {
	
	public void convertToReport(Path sourceFile, Path destinationFile) throws IOException;

}
