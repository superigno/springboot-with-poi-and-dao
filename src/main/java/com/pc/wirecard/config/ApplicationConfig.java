package com.pc.wirecard.config;

import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.pc.wirecard.model.ApplicationProperties;
import com.pc.wirecard.util.EncryptDecryptUtils;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Configuration
public class ApplicationConfig {

	@Autowired
	private Environment env;

	@Bean
	public ApplicationProperties getApplicationProperties() {
		ApplicationProperties props = new ApplicationProperties.Builder()
				.setBaseDir(env.getRequiredProperty("wirecard.dir.base"))
				.setSourceDir(env.getRequiredProperty("wirecard.dir.source"))
				.setPendingDir(env.getRequiredProperty("wirecard.dir.pending"))
				.setBackupDir(env.getRequiredProperty("wirecard.dir.backup"))
				.setInternalReportDestinationDir(env.getRequiredProperty("wirecard.dir.destination.internalreport"))
				.setExternalReportDestinationDir(env.getRequiredProperty("wirecard.dir.destination.externalreport"))
				.setDbReloadPeriodInMinutes(env.getRequiredProperty("wirecard.db.reloadperiodinminutes", Integer.class))
				.setSourceDirScanPeriodInMinutes((env.getRequiredProperty("wirecard.dir.scanperiodinminutes", Integer.class)))
				.setMailFrom(env.getRequiredProperty("wirecard.mail.from"))
				.setMailTo(env.getRequiredProperty("wirecard.mail.to"))
				.setMailSubject(env.getRequiredProperty("wirecard.mail.subject")).build();
		return props;
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public JavaMailSender javaMailSender() {
		final String host = env.getRequiredProperty("wirecard.mail.host");
		final String port = env.getRequiredProperty("wirecard.mail.port");
		final String username = env.getRequiredProperty("wirecard.mail.username");
		final String password = EncryptDecryptUtils.decrypt(env.getRequiredProperty("wirecard.mail.password"));
		final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(host);
		javaMailSender.setPort(Integer.parseInt(port));
		javaMailSender.setUsername(username);
		javaMailSender.setPassword(password);
		javaMailSender.setJavaMailProperties(getMailProperties());
		return javaMailSender;
	}

	private Properties getMailProperties() {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", env.getRequiredProperty("wirecard.mail.properties.mail.smtp.auth"));
		properties.setProperty("mail.smtp.starttls.enable", env.getRequiredProperty("wirecard.mail.properties.mail.smtp.starttls.enable"));
		return properties;
	}

}
