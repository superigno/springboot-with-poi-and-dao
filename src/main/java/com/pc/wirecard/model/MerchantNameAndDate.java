package com.pc.wirecard.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class MerchantNameAndDate {
	
	private String merchantName;
	private String date;
	
	public String getMerchantName() {
		return merchantName;
	}
	public String getDate() {
		return date;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public LocalDate getFormattedDate() {
		final DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate d = LocalDate.parse(date, f);
		return d; //using default format yyyy-MM-dd
	}
	
}
