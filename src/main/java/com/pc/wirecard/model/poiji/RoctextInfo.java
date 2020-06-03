package com.pc.wirecard.model.poiji;

import java.math.BigDecimal;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public class RoctextInfo {
	
	@ExcelRow                    
    private int rowIndex;

    @ExcelCell(0)                
    private String org;
    
    @ExcelCell(1)                
    private String merchantId;
    
    @ExcelCell(2)                
    private String merchantName;
    
    @ExcelCell(3)                
    private String terminalId;
    
    @ExcelCell(4)                
    private String batchSocNbr;
    
    @ExcelCell(5)                
    private String refBatchSrce;
    
    @ExcelCell(6)                
    private String cardNbr;
    
    @ExcelCell(7)                
    private String transDate;
    
    @ExcelCell(8)                
    private String ccy;
    
    @ExcelCell(9)                
    private BigDecimal transAmt;
    
    @ExcelCell(10)                
    private String authCode;
    
    @ExcelCell(11)                
    private String cardType;
    
    @ExcelCell(12)                
    private String onUsOffUsFlag;
    
    @ExcelCell(13)                
    private String product;
    
    @ExcelCell(14)                
    private String acqRefNum;
    
    @ExcelCell(15)                
    private String description;
    
    @ExcelCell(16)                
    private String postDate;
    
    @ExcelCell(17)                
    private String submitCcy;
    
    @ExcelCell(18)                
    private BigDecimal submitAmt;
    
    @ExcelCell(19)                
    private String subMedia;
    
    @ExcelCell(20)                
    private String logicModule;
    
    @ExcelCell(21)                
    private String qualIndicator;
    
    @ExcelCell(22)                
    private String dataEntryMode;
    
    @ExcelCell(23)                
    private String mcc;
    
    @ExcelCell(24)                
    private String gapOsMode;
    
    @ExcelCell(25)                
    private BigDecimal oGrossAmt;
    
    @ExcelCell(26)                
    private BigDecimal oComAmt;
    
    @ExcelCell(27)                
    private BigDecimal oVatAmt;
    
    @ExcelCell(28)                
    private BigDecimal oOthAmt;
    
    @ExcelCell(29)                
    private BigDecimal oNetAmt;
    
    @ExcelCell(30)                
    private String rocText;
    
    @ExcelCell(31)                
    private BigDecimal baseAmt;

	public int getRowIndex() {
		return rowIndex;
	}

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

	public BigDecimal getBaseAmt() {
		return baseAmt;
	}
    
}
