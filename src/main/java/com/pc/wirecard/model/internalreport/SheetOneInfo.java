package com.pc.wirecard.model.internalreport;

import java.math.BigDecimal;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class SheetOneInfo {
	
	private String org;
    private String merchantId;
    private String merchantName;
    private String terminalId;
    private String batchSocNbr;
    private String refBatchSrce;
    private String cardNbr;
    private String transDate;
    private String ccy;
    private BigDecimal transAmt = new BigDecimal(0);
    private String authCode;
    private String cardType;
    private String onUsOffUsFlag;
    private String product;
    private String acqRefNum;
    private String description;
    private String postDate;
    private String submitCcy;
    private BigDecimal submitAmt = new BigDecimal(0);
    private String subMedia;
    private String logicModule;
    private String qualIndicator;
    private String dataEntryMode;
    private String mcc;
    private String gapOsMode;
    private BigDecimal oGrossAmt = new BigDecimal(0);
    private BigDecimal oComAmt = new BigDecimal(0);
    private BigDecimal oVatAmt = new BigDecimal(0);
    private BigDecimal oOthAmt = new BigDecimal(0);
    private BigDecimal oNetAmt = new BigDecimal(0);
    private String rocText;
    private BigDecimal sgdAmount = new BigDecimal(0);
    private BigDecimal sgdPayment = new BigDecimal(0);
    private BigDecimal exceptionSgdAmount = new BigDecimal(0);
    
	public String getOrg() {
		return org;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public String getBatchSocNbr() {
		return batchSocNbr;
	}
	public String getRefBatchSrce() {
		return refBatchSrce;
	}
	public String getCardNbr() {
		return cardNbr;
	}
	public String getTransDate() {
		return transDate;
	}
	public String getCcy() {
		return ccy;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public String getAuthCode() {
		return authCode;
	}
	public String getCardType() {
		return cardType;
	}
	public String getOnUsOffUsFlag() {
		return onUsOffUsFlag;
	}
	public String getProduct() {
		return product;
	}
	public String getAcqRefNum() {
		return acqRefNum;
	}
	public String getDescription() {
		return description;
	}
	public String getPostDate() {
		return postDate;
	}
	public String getSubmitCcy() {
		return submitCcy;
	}
	public BigDecimal getSubmitAmt() {
		return submitAmt;
	}
	public String getSubMedia() {
		return subMedia;
	}
	public String getLogicModule() {
		return logicModule;
	}
	public String getQualIndicator() {
		return qualIndicator;
	}
	public String getDataEntryMode() {
		return dataEntryMode;
	}
	public String getMcc() {
		return mcc;
	}
	public String getGapOsMode() {
		return gapOsMode;
	}
	public BigDecimal getoGrossAmt() {
		return oGrossAmt;
	}
	public BigDecimal getoComAmt() {
		return oComAmt;
	}
	public BigDecimal getoVatAmt() {
		return oVatAmt;
	}
	public BigDecimal getoOthAmt() {
		return oOthAmt;
	}
	public BigDecimal getoNetAmt() {
		return oNetAmt;
	}
	public String getRocText() {
		return rocText;
	}
	public BigDecimal getSgdAmount() {
		return sgdAmount;
	}
	public BigDecimal getSgdPayment() {
		return sgdPayment;
	}
	public BigDecimal getExceptionSgdAmount() {
		return exceptionSgdAmount;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public void setBatchSocNbr(String batchSocNbr) {
		this.batchSocNbr = batchSocNbr;
	}
	public void setRefBatchSrce(String refBatchSrce) {
		this.refBatchSrce = refBatchSrce;
	}
	public void setCardNbr(String cardNbr) {
		this.cardNbr = cardNbr;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public void setOnUsOffUsFlag(String onUsOffUsFlag) {
		this.onUsOffUsFlag = onUsOffUsFlag;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public void setAcqRefNum(String acqRefNum) {
		this.acqRefNum = acqRefNum;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public void setSubmitCcy(String submitCcy) {
		this.submitCcy = submitCcy;
	}
	public void setSubmitAmt(BigDecimal submitAmt) {
		this.submitAmt = submitAmt;
	}
	public void setSubMedia(String subMedia) {
		this.subMedia = subMedia;
	}
	public void setLogicModule(String logicModule) {
		this.logicModule = logicModule;
	}
	public void setQualIndicator(String qualIndicator) {
		this.qualIndicator = qualIndicator;
	}
	public void setDataEntryMode(String dataEntryMode) {
		this.dataEntryMode = dataEntryMode;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public void setGapOsMode(String gapOsMode) {
		this.gapOsMode = gapOsMode;
	}
	public void setoGrossAmt(BigDecimal oGrossAmt) {
		this.oGrossAmt = oGrossAmt;
	}
	public void setoComAmt(BigDecimal oComAmt) {
		this.oComAmt = oComAmt;
	}
	public void setoVatAmt(BigDecimal oVatAmt) {
		this.oVatAmt = oVatAmt;
	}
	public void setoOthAmt(BigDecimal oOthAmt) {
		this.oOthAmt = oOthAmt;
	}
	public void setoNetAmt(BigDecimal oNetAmt) {
		this.oNetAmt = oNetAmt;
	}
	public void setRocText(String rocText) {
		this.rocText = rocText;
	}
	public void setSgdAmount(BigDecimal sgdAmount) {
		this.sgdAmount = sgdAmount;
	}
	public void setSgdPayment(BigDecimal sgdPayment) {
		this.sgdPayment = sgdPayment;
	}
	public void setExceptionSgdAmount(BigDecimal exceptionSgdAmount) {
		this.exceptionSgdAmount = exceptionSgdAmount;
	}
	
	@Override
	public String toString() {
		return "Sheet1CellInfo [org=" + org + ", merchantId=" + merchantId + ", merchantName=" + merchantName
				+ ", terminalId=" + terminalId + ", batchSocNbr=" + batchSocNbr + ", refBatchSrce=" + refBatchSrce
				+ ", cardNbr=" + cardNbr + ", transDate=" + transDate + ", ccy=" + ccy + ", transAmt=" + transAmt
				+ ", authCode=" + authCode + ", cardType=" + cardType + ", onUsOffUsFlag=" + onUsOffUsFlag
				+ ", product=" + product + ", acqRefNum=" + acqRefNum + ", description=" + description + ", postDate="
				+ postDate + ", submitCcy=" + submitCcy + ", submitAmt=" + submitAmt + ", subMedia=" + subMedia
				+ ", logicModule=" + logicModule + ", qualIndicator=" + qualIndicator + ", dataEntryMode="
				+ dataEntryMode + ", mcc=" + mcc + ", gapOsMode=" + gapOsMode + ", oGrossAmt=" + oGrossAmt
				+ ", oComAmt=" + oComAmt + ", oVatAmt=" + oVatAmt + ", oOthAmt=" + oOthAmt + ", oNetAmt=" + oNetAmt
				+ ", rocText=" + rocText + ", sgdAmount=" + sgdAmount + ", sgdPayment=" + sgdPayment
				+ ", exceptionSgdAmount=" + exceptionSgdAmount + "]";
	}

}
