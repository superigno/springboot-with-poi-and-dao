package com.pc.wirecard.core.internalreport.transformer;

import static com.pc.wirecard.constant.WirecardConstants.CURRENCY_BASE;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_DAILY_SETTLEMENT_AMT_TO_MERCHANT_FROM_PC;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_DCC_HIT_RATE;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_MERCHANT_COMMISSION;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_SUBTOTAL_DCC_OPTIN;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_SUBTOTAL_JCB_TRANSACTIONS;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_SUBTOTAL_LOCAL_CARD_TRANSACTIONS;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_SUBTOTAL_NONDCC_OPTOUT;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_TOTAL_DCCABLE;
import static com.pc.wirecard.constant.WirecardConstants.IR_SUMMARY_SUBHEADER_TOTAL_SGD;
import static com.pc.wirecard.constant.WirecardConstants.MAJOR_CURRENCIES;
import static com.pc.wirecard.constant.WirecardConstants.OPTOUT_PRODUCTS;
import static com.pc.wirecard.constant.WirecardConstants.ORDER_SUMMARY_MAJOR_CURRENCY;
import static com.pc.wirecard.constant.WirecardConstants.ORDER_SUMMARY_MINOR_CURRENCY;
import static com.pc.wirecard.constant.WirecardConstants.ORDER_SUMMARY_TOTALS;
import static com.pc.wirecard.constant.WirecardConstants.PRODUCT_JCB_LOCAL;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pc.wirecard.core.ITransform;
import com.pc.wirecard.model.internalreport.SheetOneInfo;
import com.pc.wirecard.model.internalreport.SummaryInfo;
import com.pc.wirecard.service.IMerchantService;
import com.pc.wirecard.util.WirecardUtils;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Component
public class SummaryTransformer implements ITransform<SummaryInfo, SheetOneInfo>{
	
	@Autowired
    private IMerchantService merchantService;
	
	public List<SummaryInfo> transform(List<SheetOneInfo> cellInfoList) {
		
		//Group by currency -> map to SummaryInfo -> exclude "SGD SGD" -> sort by order number and currency
		final List<SummaryInfo> summaryInfoList = cellInfoList.stream()
				.collect(Collectors.groupingBy(SheetOneInfo::getCcy,
						Collectors.mapping(info -> mapToSummaryInfo(info), SummaryCollector.collector())))
				.values().stream().filter(info -> !(CURRENCY_BASE + " " + CURRENCY_BASE).equals(info.getDescription()))
				.sorted().collect(Collectors.toList());
		
		//Totals on second part of sheet
		appendTotals(cellInfoList, summaryInfoList);
		
		return summaryInfoList;
	}
		
	private void appendTotals(final List<SheetOneInfo> sheet1InfoList, final List<SummaryInfo> summaryInfoList) {
		final SummaryInfo infoSubtotalDcc = getSubtotalDccInfo(summaryInfoList);
		final SummaryInfo infoSubtotalNonDcc = getSubtotalNonDccInfo(sheet1InfoList);
		final SummaryInfo infoMerchantCommission = getMerchantCommissionInfo(summaryInfoList);
		final SummaryInfo infoSubtotalLocal = getSubtotalLocalInfo(sheet1InfoList);
		final SummaryInfo infoSubtotalJcb = getSubtotalJcbInfo(sheet1InfoList);
		final SummaryInfo infoTotalSgd = getTotalSgdInfo(infoSubtotalDcc, infoSubtotalNonDcc, infoMerchantCommission, infoSubtotalLocal, infoSubtotalJcb);
		final SummaryInfo infoTotalSettlement = getTotalSettlementInfo(infoTotalSgd);
		final SummaryInfo infoTotalDccable = getTotalDccableInfo(infoSubtotalDcc, infoSubtotalNonDcc);
		final SummaryInfo infoDccHitRate = getDccHitRateInfo(infoSubtotalDcc, infoTotalDccable);
		final SummaryInfo infoBlankRow = new SummaryInfo();
		infoBlankRow.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfoList.add(infoSubtotalDcc);
		summaryInfoList.add(infoSubtotalNonDcc);
		summaryInfoList.add(infoMerchantCommission);
		summaryInfoList.add(infoSubtotalLocal);
		summaryInfoList.add(infoSubtotalJcb);
		summaryInfoList.add(infoTotalSgd);
		summaryInfoList.add(infoTotalSettlement);
		summaryInfoList.add(infoBlankRow); //Add blank row
		summaryInfoList.add(infoTotalDccable);
		summaryInfoList.add(infoDccHitRate);
	}


	private SummaryInfo getDccHitRateInfo(final SummaryInfo infoSubtotalDcc, final SummaryInfo infoTotalDccable) {
		final SummaryInfo summaryInfo = new SummaryInfo();
		final BigDecimal dccHitrate = infoSubtotalDcc.getTransactionAmountSgd().divide(infoTotalDccable.getSettledByCitiToPc(), 8, RoundingMode.HALF_UP);
		summaryInfo.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfo.setDescription(IR_SUMMARY_SUBHEADER_DCC_HIT_RATE);
		summaryInfo.setSettledByCitiToPc(dccHitrate);
		return summaryInfo;
	}


	private SummaryInfo getTotalDccableInfo(final SummaryInfo infoSubtotalDcc, final SummaryInfo infoSubtotalNonDcc) {
		final SummaryInfo summaryInfo = new SummaryInfo();
		final BigDecimal totalDccable = infoSubtotalDcc.getTransactionAmountSgd().add(infoSubtotalNonDcc.getTransactionAmountSgd());
		summaryInfo.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfo.setDescription(IR_SUMMARY_SUBHEADER_TOTAL_DCCABLE);
		summaryInfo.setSettledByCitiToPc(totalDccable);
		return summaryInfo;
	}


	private SummaryInfo getTotalSettlementInfo(final SummaryInfo infoTotalSgd) {
		final SummaryInfo summaryInfo = new SummaryInfo();
		summaryInfo.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfo.setDescription(IR_SUMMARY_SUBHEADER_DAILY_SETTLEMENT_AMT_TO_MERCHANT_FROM_PC);
		summaryInfo.setSettlementAmountToMerchant(infoTotalSgd.getSettlementAmountToMerchant());
		return summaryInfo;
	}


	private SummaryInfo getTotalSgdInfo(final SummaryInfo infoSubtotalDcc, final SummaryInfo infoSubtotalNonDcc,
			final SummaryInfo infoMerchantCommission, final SummaryInfo infoSubtotalLocal,
			final SummaryInfo infoSubtotalJcb) {
		
		final SummaryInfo summaryInfo = new SummaryInfo();
		
		final BigDecimal totalSgdSettledByCitiToPc = infoSubtotalNonDcc.getSettledByCitiToPc()
				.add(infoSubtotalLocal.getSettledByCitiToPc().add(infoSubtotalJcb.getSettledByCitiToPc()));
		
		final BigDecimal totalSgdSettledAmountToMerchant = infoSubtotalDcc.getSettlementAmountToMerchant()
				.add(infoSubtotalNonDcc.getSettlementAmountToMerchant()
						.add(infoMerchantCommission.getSettlementAmountToMerchant()
								.add(infoSubtotalLocal.getSettlementAmountToMerchant()
										.add(infoSubtotalJcb.getSettlementAmountToMerchant()))));
		
		summaryInfo.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfo.setDescription(IR_SUMMARY_SUBHEADER_TOTAL_SGD);
		summaryInfo.setSettledByCitiToPc(totalSgdSettledByCitiToPc);
		summaryInfo.setSettlementAmountToMerchant(totalSgdSettledAmountToMerchant);
		return summaryInfo;
	}


	private SummaryInfo getSubtotalDccInfo(final List<SummaryInfo> summaryInfoList) {
		final SummaryInfo summaryInfo = new SummaryInfo();
		final double totalTransactionAmountSgd = summaryInfoList.stream().mapToDouble(info -> info.getTransactionAmountSgd().doubleValue()).sum();
		final double totalSettlementAmountToMerchant = summaryInfoList.stream().mapToDouble(info -> info.getSettlementAmountToMerchant().doubleValue()).sum();
		final double totalMdrPayByMerchant = summaryInfoList.stream().mapToDouble(info -> info.getMdrPayByMerchant().doubleValue()).sum();
		summaryInfo.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfo.setDescription(IR_SUMMARY_SUBHEADER_SUBTOTAL_DCC_OPTIN);
		summaryInfo.setTransactionAmountSgd(new BigDecimal(totalTransactionAmountSgd));
		summaryInfo.setSettlementAmountToMerchant(new BigDecimal(totalSettlementAmountToMerchant));
		summaryInfo.setMdrPayByMerchant(new BigDecimal(totalMdrPayByMerchant));
		return summaryInfo;
	}
	
	private SummaryInfo getSubtotalNonDccInfo(final List<SheetOneInfo> sheet1InfoList) {
		final SummaryInfo summaryInfo = new SummaryInfo();
		final List<String> optOutProducts = Arrays.asList(OPTOUT_PRODUCTS);
		final List<SheetOneInfo> optOutList = sheet1InfoList.stream().filter(info -> CURRENCY_BASE.equals(info.getCcy()) && optOutProducts.contains(info.getProduct())).collect(Collectors.toList());
		
		final double totalSettledByCitiToPc = optOutList.stream().mapToDouble(info -> info.getoNetAmt().doubleValue()).sum();
		final double totalTransactionAmountSgd = optOutList.stream().mapToDouble(info -> info.getoGrossAmt().doubleValue()).sum();
		final double totalSettlementAmountToMerchant = totalSettledByCitiToPc;
		final double totalMdrPayByMerchant = totalTransactionAmountSgd - totalSettlementAmountToMerchant;
		
		summaryInfo.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfo.setDescription(IR_SUMMARY_SUBHEADER_SUBTOTAL_NONDCC_OPTOUT);
		summaryInfo.setSettledByCitiToPc(new BigDecimal(totalSettledByCitiToPc));
		summaryInfo.setTransactionAmountSgd(new BigDecimal(totalTransactionAmountSgd));
		summaryInfo.setSettlementAmountToMerchant(new BigDecimal(totalSettlementAmountToMerchant));
		summaryInfo.setMdrPayByMerchant(new BigDecimal(totalMdrPayByMerchant));
		
		return summaryInfo;
		
	}
	
	private SummaryInfo getMerchantCommissionInfo(final List<SummaryInfo> summaryInfoList) {
		final SummaryInfo summaryInfo = new SummaryInfo();
		final double totalSettlementAmountToMerchant = summaryInfoList.stream().mapToDouble(info -> info.getMerchantCommission().doubleValue()).sum();
		summaryInfo.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfo.setDescription(IR_SUMMARY_SUBHEADER_MERCHANT_COMMISSION);
		summaryInfo.setSettlementAmountToMerchant(new BigDecimal(totalSettlementAmountToMerchant));
		return summaryInfo;
	}
	
	private SummaryInfo getSubtotalLocalInfo(final List<SheetOneInfo> sheet1InfoList) {
		final SummaryInfo summaryInfo = new SummaryInfo();
		final List<String> optOutProductsPlusJcb = Arrays.asList(OPTOUT_PRODUCTS);
		final List<SheetOneInfo> localList = sheet1InfoList.stream()
				.filter(info -> CURRENCY_BASE.equals(info.getCcy())
						&& (!optOutProductsPlusJcb.contains(info.getProduct())
								&& !PRODUCT_JCB_LOCAL.equals(info.getProduct())))
				.collect(Collectors.toList());
		
		final double totalSettledByCitiToPc = localList.stream().mapToDouble(info -> info.getoNetAmt().doubleValue()).sum();
		final double totalTransactionAmountSgd = localList.stream().mapToDouble(info -> info.getoGrossAmt().doubleValue()).sum();
		final double totalSettlementAmountToMerchant = totalSettledByCitiToPc;
		final double totalMdrPayByMerchant = totalTransactionAmountSgd - totalSettlementAmountToMerchant;
		
		summaryInfo.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfo.setDescription(IR_SUMMARY_SUBHEADER_SUBTOTAL_LOCAL_CARD_TRANSACTIONS);
		summaryInfo.setSettledByCitiToPc(new BigDecimal(totalSettledByCitiToPc));
		summaryInfo.setTransactionAmountSgd(new BigDecimal(totalTransactionAmountSgd));
		summaryInfo.setSettlementAmountToMerchant(new BigDecimal(totalSettlementAmountToMerchant));
		summaryInfo.setMdrPayByMerchant(new BigDecimal(totalMdrPayByMerchant));
		
		return summaryInfo;
		
	}
	
	private SummaryInfo getSubtotalJcbInfo(final List<SheetOneInfo> sheet1InfoList) {
		final SummaryInfo summaryInfo = new SummaryInfo();
		final List<SheetOneInfo> jcbList = sheet1InfoList.stream().filter(info -> CURRENCY_BASE.equals(info.getCcy()) && PRODUCT_JCB_LOCAL.equals(info.getProduct())).collect(Collectors.toList());
		
		final double totalSettledByCitiToPc = jcbList.stream().mapToDouble(info -> info.getoNetAmt().doubleValue()).sum();
		final double totalTransactionAmountSgd = jcbList.stream().mapToDouble(info -> info.getoGrossAmt().doubleValue()).sum();
		final double totalSettlementAmountToMerchant = totalSettledByCitiToPc;
		final double totalMdrPayByMerchant = totalTransactionAmountSgd - totalSettlementAmountToMerchant;
		
		summaryInfo.setOrder(ORDER_SUMMARY_TOTALS);
		summaryInfo.setDescription(IR_SUMMARY_SUBHEADER_SUBTOTAL_JCB_TRANSACTIONS);
		summaryInfo.setSettledByCitiToPc(new BigDecimal(totalSettledByCitiToPc));
		summaryInfo.setTransactionAmountSgd(new BigDecimal(totalTransactionAmountSgd));
		summaryInfo.setSettlementAmountToMerchant(new BigDecimal(totalSettlementAmountToMerchant));
		summaryInfo.setMdrPayByMerchant(new BigDecimal(totalMdrPayByMerchant));
		
		return summaryInfo;
		
	}
	
	private SummaryInfo mapToSummaryInfo(SheetOneInfo cellInfo) {
		final SummaryInfo summaryInfo = new SummaryInfo();
		final boolean isMajorCurrency = Arrays.asList(MAJOR_CURRENCIES).contains(cellInfo.getCcy());
		final String currency = isMajorCurrency ? cellInfo.getCcy() : CURRENCY_BASE+" "+cellInfo.getCcy();
		final int order = isMajorCurrency ? ORDER_SUMMARY_MAJOR_CURRENCY : ORDER_SUMMARY_MINOR_CURRENCY;
		final BigDecimal merchantCommissionRate = merchantService.getMerchantCommissionRateMap().get(cellInfo.getMerchantId());
		
		summaryInfo.setOrder(order);
		summaryInfo.setDescription(currency);
		summaryInfo.setGrossAmountFromCiti(cellInfo.getoGrossAmt());
		summaryInfo.setSettledByCitiToPc(cellInfo.getoNetAmt());
		summaryInfo.setCitiMdrCharge(cellInfo.getoComAmt());
		summaryInfo.setTransactionAmountMatch(cellInfo.getTransAmt());
		summaryInfo.setTransactionAmountSgd(cellInfo.getSgdAmount());
		summaryInfo.setSettlementAmountToMerchant(cellInfo.getSgdPayment());
		summaryInfo.setMdrPayByMerchant(cellInfo.getSgdAmount().subtract(cellInfo.getSgdPayment()));
		summaryInfo.setMerchantCommission(WirecardUtils.getMerchantCommission(cellInfo.getSgdAmount(), merchantCommissionRate));
		
		return summaryInfo;
	}

}
