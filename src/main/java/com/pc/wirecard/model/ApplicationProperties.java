package com.pc.wirecard.model;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public final class ApplicationProperties {

	private final String baseDir;
	private final String sourceDir;
	private final String pendingDir;
	private final String backupDir;
	private final String internalReportDestinationDir;
	private final String externalReportDestinationDir;

	private final int dbReloadPeriodInMinutes;
	private final int sourceDirScanPeriodInMinutes;

	private final String mailFrom;
	private final String mailTo;
	private final String mailSubject;

	private ApplicationProperties(String baseDir, String sourceDir, String internalReportDestinationDir,
			String externalReportDestinationDir, String pendingDir, String backupDir, int dbReloadPeriodInMinutes,
			int sourceDirScanPeriodInMinutes, String mailFrom, String mailTo, String mailSubject) {
		this.baseDir = baseDir;
		this.sourceDir = sourceDir;
		this.internalReportDestinationDir = internalReportDestinationDir;
		this.externalReportDestinationDir = externalReportDestinationDir;
		this.pendingDir = pendingDir;
		this.backupDir = backupDir;
		this.dbReloadPeriodInMinutes = dbReloadPeriodInMinutes;
		this.sourceDirScanPeriodInMinutes = sourceDirScanPeriodInMinutes;
		this.mailFrom = mailFrom;
		this.mailTo = mailTo;
		this.mailSubject = mailSubject;
	}

	public String getBackupDir() {
		return backupDir;
	}

	public String getBaseDir() {
		return baseDir;
	}

	public String getSourceDir() {
		return sourceDir;
	}

	public String getInternalReportDestinationDir() {
		return internalReportDestinationDir;
	}

	public String getExternalReportDestinationDir() {
		return externalReportDestinationDir;
	}

	public String getPendingDir() {
		return pendingDir;
	}

	public int getDbReloadPeriodInMinutes() {
		return dbReloadPeriodInMinutes;
	}

	public int getSourceDirScanPeriodInMinutes() {
		return sourceDirScanPeriodInMinutes;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public String getMailTo() {
		return mailTo;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public static class Builder {

		private String baseDir;
		private String sourceDir;
		private String internalReportDestinationDir;
		private String externalReportDestinationDir;
		private String pendingDir;
		private String backupDir;

		private int dbReloadPeriodInMinutes;
		private int sourceDirScanPeriodInMinutes;

		private String mailFrom;
		private String mailTo;
		private String mailSubject;

		public Builder setBaseDir(String baseDir) {
			this.baseDir = baseDir;
			return this;
		}

		public Builder setSourceDir(String sourceDir) {
			this.sourceDir = sourceDir;
			return this;
		}

		public Builder setInternalReportDestinationDir(String internalReportDestinationDir) {
			this.internalReportDestinationDir = internalReportDestinationDir;
			return this;
		}

		public Builder setExternalReportDestinationDir(String externalReportDestinationDir) {
			this.externalReportDestinationDir = externalReportDestinationDir;
			return this;
		}

		public Builder setPendingDir(String pendingDir) {
			this.pendingDir = pendingDir;
			return this;
		}

		public Builder setBackupDir(String backupDir) {
			this.backupDir = backupDir;
			return this;
		}

		public Builder setDbReloadPeriodInMinutes(int dbReloadPeriodInMinutes) {
			this.dbReloadPeriodInMinutes = dbReloadPeriodInMinutes;
			return this;
		}

		public Builder setSourceDirScanPeriodInMinutes(int sourceDirScanPeriodInMinutes) {
			this.sourceDirScanPeriodInMinutes = sourceDirScanPeriodInMinutes;
			return this;
		}

		public Builder setMailFrom(String mailFrom) {
			this.mailFrom = mailFrom;
			return this;
		}

		public Builder setMailTo(String mailTo) {
			this.mailTo = mailTo;
			return this;
		}

		public Builder setMailSubject(String mailSubject) {
			this.mailSubject = mailSubject;
			return this;
		}

		public ApplicationProperties build() {
			return new ApplicationProperties(baseDir, sourceDir, internalReportDestinationDir,
					externalReportDestinationDir, pendingDir, backupDir, dbReloadPeriodInMinutes,
					sourceDirScanPeriodInMinutes, mailFrom, mailTo, mailSubject);
		}

	}

}
