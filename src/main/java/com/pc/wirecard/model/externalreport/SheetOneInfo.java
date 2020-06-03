package com.pc.wirecard.model.externalreport;

import java.math.BigDecimal;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class SheetOneInfo {
	
	private String mid;
	private String date;
	private String cardNumber;    
    private String authorisationNumber;
    private BigDecimal transactionSales = new BigDecimal(0);
    private BigDecimal mdrRate = new BigDecimal(0);
    private BigDecimal commission = new BigDecimal(0);
    private BigDecimal creditAmount = new BigDecimal(0);
    private String remark;
    private String rrn;
    
	public String getMid() {
		return mid;
	}
	public String getDate() {
		return date;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public String getAuthorisationNumber() {
		return authorisationNumber;
	}
	public BigDecimal getTransactionSales() {
		return transactionSales;
	}
	public BigDecimal getMdrRate() {
		return mdrRate;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public String getRemark() {
		return remark;
	}
	public String getRrn() {
		return rrn;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public void setAuthorisationNumber(String authorisationNumber) {
		this.authorisationNumber = authorisationNumber;
	}
	public void setTransactionSales(BigDecimal transactionSales) {
		this.transactionSales = transactionSales;
	}
	public void setMdrRate(BigDecimal mdrRate) {
		this.mdrRate = mdrRate;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	
	@Override
	public String toString() {
		return "SheetOneInfo [mid=" + mid + ", date=" + date + ", cardNumber=" + cardNumber + ", authorisationNumber="
				+ authorisationNumber + ", transactionSales=" + transactionSales + ", mdrRate=" + mdrRate
				+ ", commission=" + commission + ", creditAmount=" + creditAmount + ", remark=" + remark + ", rrn="
				+ rrn + "]";
	}
    
}
