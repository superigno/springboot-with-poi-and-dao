package com.pc.wirecard.constant;

import java.math.BigDecimal;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public final class WirecardConstants {
	
	private WirecardConstants() {}
	
	public static final BigDecimal ZERO = new BigDecimal(0);
	public static final String FILENAME_PATTERN = "(PUC-.+)-(\\d\\d-[a-zA-Z]{3}-\\d\\d\\d\\d).xls";
	public static final int BIGDECIMAL_ROUNDING_SCALE = 2;
	public static final int BIGDECIMAL_QUOTIENT_SCALE = 8;
	
	public static final String LOGIC_MODULE_SALE = "201";
	public static final String LOGIC_MODULE_REFUND = "301";
	
	public static final String IR_HEADER_ORG = "ORG";
	public static final String IR_HEADER_MERCHANTID = "MERCHANTID";
	public static final String IR_HEADER_MERCHANTNAME = "MERCHANTNAME";
	public static final String IR_HEADER_TERMINALID = "TERMINALID";
	public static final String IR_HEADER_BATCHSOCNBR = "BATCHSOCNBR";
	public static final String IR_HEADER_REFBATCHSRCE = "REFBATCHSRCE";
	public static final String IR_HEADER_CARDNBR = "CARDNBR";
	public static final String IR_HEADER_TRANSDATE = "TRANSDATE";
	public static final String IR_HEADER_CCY = "CCY";
	public static final String IR_HEADER_TRANSAMT = "TRANSAMT";
	public static final String IR_HEADER_AUTHCODE = "AUTHCODE";
	public static final String IR_HEADER_CARDTYPE = "CARDTYPE";
	public static final String IR_HEADER_ONUSOFFUSFLAG = "ONUSOFFUSFLAG";
	public static final String IR_HEADER_PRODUCT = "PRODUCT";
	public static final String IR_HEADER_ACQREFNUM = "ACQREFNUM";
	public static final String IR_HEADER_DESCRIPTION = "DESCRIPTION";
	public static final String IR_HEADER_POSTDATE = "POSTDATE";
	public static final String IR_HEADER_SUBMITCCY = "SUBMITCCY";
	public static final String IR_HEADER_SUBMITAMT = "SUBMITAMT";
	public static final String IR_HEADER_SUBMEDIA = "SUBMEDIA";
	public static final String IR_HEADER_LOGICMODULE = "LOGICMODULE";
	public static final String IR_HEADER_QUALINDICATOR = "QUALINDICATOR";
	public static final String IR_HEADER_DATAENTRYMODE= "DATAENTRYMODE";
	public static final String IR_HEADER_MCC = "MCC";
	public static final String IR_HEADER_GAPOSMODE = "GAPOSMODE";
	public static final String IR_HEADER_O_GROSSAMT = "O_GROSSAMT";
	public static final String IR_HEADER_O_COMAMT = "O_COMAMT";
	public static final String IR_HEADER_O_VATAMT = "O_VATAMT";
	public static final String IR_HEADER_O_OTHAMT = "O_OTHAMT";
	public static final String IR_HEADER_O_NETAMT = "O_NETAMT";
	public static final String IR_HEADER_ROCTEXT = "ROC TEXT";
	public static final String IR_HEADER_SGD_AMOUNT = "SGD_AMOUNT";
	public static final String IR_HEADER_SGD_PAYMENT = "SGD_PAYMENT";
	public static final String IR_HEADER_EXCEPTION_SGD_AMOUNT = "EXCEPTION_SGD_AMOUNT";
	
	public static final String IR_SUMMARY_HEADER_DESCRIPTION = "DESCRIPTION";
	public static final String IR_SUMMARY_HEADER_GROSS_AMT_FROM_CITI = "GROSS AMOUNT FROM CITI";
	public static final String IR_SUMMARY_HEADER_SETTLED_BY_CITI_TO_PC = "SETTLED BY CITI TO PC";
	public static final String IR_SUMMARY_HEADER_CITI_MDR_CHARGE = "CITI MDR CHARGE";
	public static final String IR_SUMMARY_HEADER_TRANSACTION_AMOUNT_MATCH_IN_DB = "TRANSACTION AMOUNT MATCH IN DB (FOREIGN)";
	public static final String IR_SUMMARY_HEADER_TRANSACTION_AMOUNT_SGD = "TRANSACTION AMOUNT (SGD)";
	public static final String IR_SUMMARY_HEADER_SETTLEMENT_AMOUNT_TO_MERCHANT = "SETTLEMENT AMOUNT TO {MERCHANT_NAME} (SGD)";
	public static final String IR_SUMMARY_HEADER_MDR_PAY_BY_MERCHANT = "MDR PAY BY MERCHANT";
	public static final String IR_SUMMARY_HEADER_MERCHANT_COMMISSION = "MERCHANT COMMISSION";
	
	public static final String IR_SUMMARY_SUBHEADER_SUBTOTAL_DCC_OPTIN = "Subtotal DCC (Opt-In)";
	public static final String IR_SUMMARY_SUBHEADER_SUBTOTAL_NONDCC_OPTOUT = "Subtotal Non DCC (Opt-Out)";
	public static final String IR_SUMMARY_SUBHEADER_MERCHANT_COMMISSION = "Merchant Commission";
	public static final String IR_SUMMARY_SUBHEADER_SUBTOTAL_LOCAL_CARD_TRANSACTIONS = "Subtotal Local Card Transactions";
	public static final String IR_SUMMARY_SUBHEADER_SUBTOTAL_JCB_TRANSACTIONS = "Subtotal JCB Transactions";
	public static final String IR_SUMMARY_SUBHEADER_TOTAL_SGD = "Total SGD";
	public static final String IR_SUMMARY_SUBHEADER_DAILY_SETTLEMENT_AMT_TO_MERCHANT_FROM_PC = "Daily Settlement Amt to {MERCHANT_NAME} from PC";
	public static final String IR_SUMMARY_SUBHEADER_TOTAL_DCCABLE = "Total DCCable";
	public static final String IR_SUMMARY_SUBHEADER_DCC_HIT_RATE = "DCC Hit Rate";
	
	public static final String ER_HEADER_MID = "MID";
	public static final String ER_HEADER_DATE = "Date";
	public static final String ER_HEADER_CARD_NO = "Card No.";
	public static final String ER_HEADER_AUTHORISATION_NUMBER = "Authorisation number";
	public static final String ER_HEADER_TRANSACTION_SALES = "Transaction sales";
	public static final String ER_HEADER_MDR_RATE = "MDR Rate";
	public static final String ER_HEADER_COMMISSION = "Commision";
	public static final String ER_HEADER_CREDIT_AMOUNT = "Credit amount";
	public static final String ER_HEADER_REMARK = "Remark";
	public static final String ER_HEADER_RRN_OCENIA = "RRN (Ocenia)";
		
	public static final String CURRENCY_BASE = "SGD";
	public static final String CURRENCY_MAJOR_AUD = "AUD";
	public static final String CURRENCY_MAJOR_CHF = "CHF";
	public static final String CURRENCY_MAJOR_EUR = "EUR";
	public static final String CURRENCY_MAJOR_GBP = "GBP";
	public static final String CURRENCY_MAJOR_HKD = "HKD";
	public static final String CURRENCY_MAJOR_JPY = "JPY";
	public static final String CURRENCY_MAJOR_NZD = "NZD";
	public static final String CURRENCY_MAJOR_USD = "USD"; 
	
	public static final int ORDER_SUMMARY_MAJOR_CURRENCY = 1;
	public static final int ORDER_SUMMARY_MINOR_CURRENCY = 2;
	public static final int ORDER_SUMMARY_TOTALS = 3;
	
	/*
	 Product 1: Citibank Singapore Issued Visa card products

	 Product 2: Citibank Singapore Issued Mastercard card products
	
	 Product 3: Other Local Singapore Bank Issued Visa card products
	
	 Product 4: Other Local Singapore Bank Issued Mastercard card products
	
	 Product 5: Foreign Bank Issued Visa card products
	
	 Product 6: Foreign Bank Issued Mastercard card products
	
	 Product 7: Foreign Bank Issued Visa or Mastercard (New foreign bins that are not updated in system)
	
	 Product 8: JCB card products issued by Citi Singapore
	
	 Product 9: JCB card products issued by local banks in Singapore
	
	 Product 10: JCB card products issued by foreign banks
	  
	*/
	public static final String PRODUCT_FOREIGN_VISA = "05";
	public static final String PRODUCT_FOREIGN_MASTERCARD = "06";
	public static final String PRODUCT_FOREIGN_VISA_MASTERCARD = "07";	
	public static final String PRODUCT_JCB_LOCAL = "09";
	
	public static final String[] OPTOUT_PRODUCTS = {PRODUCT_FOREIGN_VISA,PRODUCT_FOREIGN_MASTERCARD,PRODUCT_FOREIGN_VISA_MASTERCARD};
	
	public static final String[] MAJOR_CURRENCIES = {CURRENCY_MAJOR_AUD, CURRENCY_MAJOR_CHF, CURRENCY_MAJOR_EUR, CURRENCY_MAJOR_GBP, CURRENCY_MAJOR_HKD, CURRENCY_MAJOR_JPY, CURRENCY_MAJOR_NZD, CURRENCY_MAJOR_USD};

}
