package com.pc.wirecard;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pc.wirecard.model.ApplicationProperties;
import com.pc.wirecard.model.Email;
import com.pc.wirecard.model.MerchantNameAndDate;
import com.pc.wirecard.service.IEmailService;
import com.pc.wirecard.service.IReportService;
import com.pc.wirecard.service.MerchantService;
import com.pc.wirecard.util.FileUtils;
import com.pc.wirecard.util.WirecardUtils;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Component
public class WirecardMain {
	
	private static final Logger logger = LoggerFactory.getLogger(WirecardMain.class);
	
	@Autowired
	private ApplicationProperties props;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private IReportService internalReportService;
	
	@Autowired
	private IReportService externalReportService;
	
	@Autowired
	private IEmailService emailService;
	
	@Scheduled(fixedRateString = "#{@getApplicationProperties.getDbReloadPeriodInMinutes() * 60000}")
	public void loadMerchantInfo() {
		merchantService.loadMerchantCommissionRateInMemory();
		logger.debug("Merchant info reloaded.");
		
	}
	
	//initialDelay - wanna make sure loadMerchantInfo runs first when started together
	@Scheduled(fixedRateString = "#{@getApplicationProperties.getSourceDirScanPeriodInMinutes() * 60000}", initialDelay = 1000)
	public void run() {
		
		final Path sourceDir = Paths.get(props.getSourceDir());
		final Path internalReportDestinationDir = Paths.get(props.getInternalReportDestinationDir());
		final Path externalReportDestinationDir = Paths.get(props.getExternalReportDestinationDir());
		final File[] files = sourceDir.toFile().listFiles();
		int successCtr = 0;
		int failedCtr = 0;
		final StringBuilder sb = new StringBuilder(); 

		logger.info("Scanning directory {} ...", sourceDir);
		logger.info("Files found: " + files.length);

		for (File file : files) {
			final Path sourceFilePath = file.toPath();
			try {
				logger.info("Processing [{}] ...", file.getName());
				internalReportService.convertToReport(sourceFilePath, internalReportDestinationDir);
				externalReportService.convertToReport(sourceFilePath, externalReportDestinationDir);
				moveToBackupFolder(sourceFilePath);
				successCtr++;				
			} catch (Exception e) {
				logger.error("Error converting report {}: {}", file.getName(), e.getMessage());
				logger.error("Details:", e);
				moveToPendingFolder(sourceFilePath);
				sb.append(file.getName()+" - "+e.getMessage());
				sb.append(System.lineSeparator());
				sb.append(System.lineSeparator());
				failedCtr++;
			}
		}
		
		logger.info("---------------------");
		logger.info("Total: {}, Failed: {}", successCtr+failedCtr, failedCtr);
		
		if (sb.length() > 0) {
			sendEmailForFailedTransactions(sb);
		}		
		
		logger.info("======== End ========");
		
	}
	
	private void moveToPendingFolder(Path sourceFile) {
		final Path pendingDir = Paths.get(props.getPendingDir());
		FileUtils.moveToFolder(sourceFile, pendingDir);
	}
	
	private void moveToBackupFolder(Path sourceFile) {
		final Path pendingDir = Paths.get(props.getBackupDir());
		final MerchantNameAndDate md = WirecardUtils.extractMerchantNameAndDateFromFilename(sourceFile.getFileName().toString());
		FileUtils.moveToDatedFolder(sourceFile, pendingDir, md.getFormattedDate());
	}
	
	private void sendEmailForFailedTransactions(StringBuilder sb) {
		logger.info("Sending email notification for failed reports...");
		sb.append(System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("File(s) have been moved to "+props.getPendingDir()+". See logs for details.");
		sendEmail(sb.toString());
	}
	
	private void sendEmail(String message) {		
		try {
			Email email = new Email();
			email.setFrom(props.getMailFrom());
			email.setTo(props.getMailTo().split(","));
			email.setSubject(props.getMailSubject());
			email.setMessageText(message);
			emailService.sendMail(email);
			logger.info("Email sent.");
		} catch (Exception e) {
			logger.error("Error in sending email", e);
		}				
	}
	
	/*
	private void sendEmailInNewThread(String message) {
		new Thread(
			new Runnable() {
				public void run() {
					try {
						Email email = new Email();
						email.setFrom(props.getMailFrom());
						email.setTo(props.getMailTo().split(","));
						email.setSubject(props.getMailSubject());
						email.setMessageText(message);
						emailService.sendMail(email);
						logger.info("Email sent");
					} catch (Exception e) {
						logger.error("Error in sending email: ", e);
					}
				}
			}			
		).start();
	}*/

}
