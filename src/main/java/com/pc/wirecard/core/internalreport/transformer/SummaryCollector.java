package com.pc.wirecard.core.internalreport.transformer;

import java.util.stream.Collector;

import com.pc.wirecard.model.internalreport.SummaryInfo;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 * Aggregates the sum of columns per currency
 * 
 */
public class SummaryCollector {
	
	public static Collector<SummaryInfo, SummaryInfo, SummaryInfo> collector() {
	    return Collector.of(
	    		SummaryInfo::new, 
	    		SummaryCollector::merge,
	            (l, r) -> {
	                merge(l, r);
	                return l;
	            });
	}
	
	public static void merge(final SummaryInfo first, final SummaryInfo second) {
		first.setOrder(second.getOrder());
		first.setDescription(second.getDescription());
		first.setGrossAmountFromCiti(first.getGrossAmountFromCiti().add(second.getGrossAmountFromCiti()));
		first.setSettledByCitiToPc(first.getSettledByCitiToPc().add(second.getSettledByCitiToPc()));
		first.setCitiMdrCharge(first.getCitiMdrCharge().add(second.getCitiMdrCharge()));
		first.setTransactionAmountMatch(first.getTransactionAmountMatch().add(second.getTransactionAmountMatch()));
		first.setTransactionAmountSgd(first.getTransactionAmountSgd().add(second.getTransactionAmountSgd()));
		first.setSettlementAmountToMerchant(first.getSettlementAmountToMerchant().add(second.getSettlementAmountToMerchant()));
		first.setMdrPayByMerchant(first.getMdrPayByMerchant().add(second.getMdrPayByMerchant()));
		first.setMerchantCommission(first.getMerchantCommission().add(second.getMerchantCommission()));		
	}

}
