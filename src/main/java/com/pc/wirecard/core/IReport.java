package com.pc.wirecard.core;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public interface IReport<T> {
	
	public Workbook generateReport(final SourceFile<T> sourceFile);
	public void saveToFile(Path destination) throws IOException;

}
