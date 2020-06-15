package com.pc.wirecard.model.internalreport;

import java.math.BigDecimal;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class SummaryInfo implements Comparable<SummaryInfo> {
	
	private int order;
	private String description;
    private BigDecimal grossAmountFromCiti = BigDecimal.ZERO;
    private BigDecimal settledByCitiToPc = BigDecimal.ZERO;
    private BigDecimal citiMdrCharge = BigDecimal.ZERO;
    private BigDecimal transactionAmountMatch = BigDecimal.ZERO;
    private BigDecimal transactionAmountSgd = BigDecimal.ZERO;
    private BigDecimal settlementAmountToMerchant = BigDecimal.ZERO;
    private BigDecimal mdrPayByMerchant = BigDecimal.ZERO;
    private BigDecimal merchantCommission = BigDecimal.ZERO;
    
	public String getDescription() {
		return description;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public BigDecimal getGrossAmountFromCiti() {
		return grossAmountFromCiti;
	}
	public BigDecimal getSettledByCitiToPc() {
		return settledByCitiToPc;
	}
	public BigDecimal getCitiMdrCharge() {
		return citiMdrCharge;
	}
	public BigDecimal getTransactionAmountMatch() {
		return transactionAmountMatch;
	}
	public BigDecimal getTransactionAmountSgd() {
		return transactionAmountSgd;
	}
	public BigDecimal getSettlementAmountToMerchant() {
		return settlementAmountToMerchant;
	}
	public BigDecimal getMdrPayByMerchant() {
		return mdrPayByMerchant;
	}
	public BigDecimal getMerchantCommission() {
		return merchantCommission;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setGrossAmountFromCiti(BigDecimal grossAmountFromCiti) {
		this.grossAmountFromCiti = grossAmountFromCiti;
	}
	public void setSettledByCitiToPc(BigDecimal settledByCitiToPc) {
		this.settledByCitiToPc = settledByCitiToPc;
	}
	public void setCitiMdrCharge(BigDecimal citiMdrCharge) {
		this.citiMdrCharge = citiMdrCharge;
	}
	public void setTransactionAmountMatch(BigDecimal transactionAmountMatch) {
		this.transactionAmountMatch = transactionAmountMatch;
	}
	public void setTransactionAmountSgd(BigDecimal transactionAmountSgd) {
		this.transactionAmountSgd = transactionAmountSgd;
	}
	public void setSettlementAmountToMerchant(BigDecimal settlementAmountToMerchant) {
		this.settlementAmountToMerchant = settlementAmountToMerchant;
	}
	public void setMdrPayByMerchant(BigDecimal mdrPayByMerchant) {
		this.mdrPayByMerchant = mdrPayByMerchant;
	}
	public void setMerchantCommission(BigDecimal merchantCommission) {
		this.merchantCommission = merchantCommission;
	}
	
	@Override
	public String toString() {
		return "SummaryInfo [order=" + order + ", description=" + description + ", grossAmountFromCiti="
				+ grossAmountFromCiti + ", settledByCitiToPc=" + settledByCitiToPc + ", citiMdrCharge=" + citiMdrCharge
				+ ", transactionAmountMatch=" + transactionAmountMatch + ", transactionAmountSgd="
				+ transactionAmountSgd + ", settlementAmountToMerchant=" + settlementAmountToMerchant
				+ ", mdrPayByMerchant=" + mdrPayByMerchant + ", merchantCommission=" + merchantCommission + "]";
	}
	
	@Override
	public int compareTo(SummaryInfo o) {
		final int compareOrder = this.order - o.order;
        if (compareOrder != 0) {
           return compareOrder;
        }        
		return this.description.compareTo(o.getDescription());
	}
	
}
