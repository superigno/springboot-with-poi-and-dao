package com.pc.wirecard.core.externalreport.transformer;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pc.wirecard.constant.WirecardConstants;
import com.pc.wirecard.core.ITransform;
import com.pc.wirecard.model.externalreport.SheetOneInfo;
import com.pc.wirecard.model.poiji.RoctextInfo;
import com.pc.wirecard.service.IMerchantService;
import com.pc.wirecard.util.WirecardUtils;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Component("externalSheetOneTransformer")
public class SheetOneTransformer implements ITransform<SheetOneInfo, RoctextInfo>{
	
	@Autowired
    private IMerchantService merchantService;
	
	public List<SheetOneInfo> transform(List<RoctextInfo> list) {		
		List<SheetOneInfo> cellInfoList = list.stream().map(this::map).collect(Collectors.toList());
		return cellInfoList;
	}
	
	private SheetOneInfo map(RoctextInfo rocInfo) {
		final BigDecimal merchantCommissionRate = merchantService.getMerchantCommissionRateMap().get(rocInfo.getMerchantId());
		final SheetOneInfo info = new SheetOneInfo();
		final boolean isRefund = WirecardConstants.LOGIC_MODULE_REFUND.equals(rocInfo.getLogicModule());
		final BigDecimal baseAmount = rocInfo.getBaseAmt() == null ? BigDecimal.ZERO : rocInfo.getBaseAmt();
		BigDecimal sgdAmount = WirecardConstants.CURRENCY_BASE.equals(rocInfo.getCcy()) ? rocInfo.getTransAmt() : baseAmount;
		BigDecimal mdrAmount = WirecardUtils.getMdrAmount(rocInfo.getoComAmt(), rocInfo.getoGrossAmt(), sgdAmount);
		BigDecimal commission = WirecardUtils.getMerchantCommission(sgdAmount, merchantCommissionRate);
		BigDecimal creditAmount = sgdAmount.subtract(mdrAmount).add(commission);
		
		if (isRefund) {
			sgdAmount = WirecardUtils.returnAsNegative(sgdAmount);
			commission = WirecardUtils.returnAsNegative(commission);		
			mdrAmount = WirecardUtils.returnAsNegative(mdrAmount);
			creditAmount = WirecardUtils.returnAsNegative(creditAmount);		
		}		
		
		info.setMid(rocInfo.getMerchantId());
		info.setDate(rocInfo.getTransDate());
		info.setCardNumber(rocInfo.getCardNbr());
		info.setAuthorisationNumber(rocInfo.getAuthCode());
		info.setTransactionSales(sgdAmount);
		info.setMdrRate(mdrAmount);
		info.setCommission(commission);
		info.setCreditAmount(creditAmount);
		info.setRemark(""); //leave blank
		info.setRrn(rocInfo.getRocText());
		return info;		
	}

}
