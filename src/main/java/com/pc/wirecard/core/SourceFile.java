package com.pc.wirecard.core;

import java.nio.file.Path;
import java.util.List;

import com.pc.wirecard.util.WirecardUtils;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class SourceFile<T> {
	
	private Path filePath;
	private List<T> list;
	
	public SourceFile(Path filePath, Class<T> type) {
		this.filePath = filePath;	
		this.list = WirecardUtils.convertToList(filePath, type);
	}

	public Path getFilePath() {
		return filePath;
	}

	public String getFilename() {
		return filePath.getFileName().toString();
	}

	public List<T> asList() {
		return list;
	}
	
}
