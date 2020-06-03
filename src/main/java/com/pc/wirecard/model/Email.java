package com.pc.wirecard.model;

/**
 * @author gino.q
 * @date April 8, 2020
 *
 */
public class Email {

	private String from;
	private String[] to;
	private String subject;
	private String messageText;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String... to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

}
