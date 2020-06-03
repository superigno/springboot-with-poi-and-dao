package com.pc.wirecard.service;

import com.pc.wirecard.model.Email;

/**
 * @author gino.q
 * @date April 8, 2020
 *
 */
public interface IEmailService {
	
	public void sendMail(final Email email);
	public void sendMailWithAttachment(final Email email, final String attachmentName, final String filePath) throws Exception;

}
