package com.pc.wirecard.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(WirecardUtils.class);
	
	public static void moveToFolder(final Path sourcePath, final Path destinationPath) {	
		try {
			Files.createDirectories(destinationPath);
			Files.move(sourcePath, destinationPath.resolve(sourcePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
		    logger.trace("{} moved to {}.", sourcePath.getFileName(), destinationPath.toString());
		} catch (Exception e) {
			logger.info("Failed to move file {} to folder.", sourcePath.getFileName());
			logger.error("Error:", e);
		}	
	}
	
	public static void moveToDatedFolder(final Path sourcePath, final Path destinationPath, final LocalDate date) {
		final Path datedDestinationPath = getDatedPath(destinationPath, date);
		logger.trace("Dated destination path: {}", datedDestinationPath);		
		moveToFolder(sourcePath, datedDestinationPath);	
	}
	
	public static Path getDatedPath(final Path path, final LocalDate date) {
		final String year = String.valueOf(date.getYear());
		final String month = String.format("%02d", date.getMonth().getValue());
		final String dayDate = String.format("%02d", date.getDayOfMonth());
		final String sPath = path + File.separator + year + File.separator + month + File.separator + dayDate;		
		return Paths.get(sPath);
	}
}
