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
		final BigDecimal baseAmount = rocInfo.getBaseAmt() == null ? new BigDecimal(0) : rocInfo.getBaseAmt();
		final BigDecimal sgdAmount = WirecardConstants.CURRENCY_BASE.equals(rocInfo.getCcy()) ? new BigDecimal(0) : baseAmount;
		final BigDecimal mdrRate = WirecardUtils.getMdrAmount(rocInfo);
		final BigDecimal commission = WirecardUtils.getMerchantCommission(sgdAmount, merchantCommissionRate);
		final BigDecimal creditAmount = sgdAmount.subtract(mdrRate).add(commission);		
		
		info.setMid(rocInfo.getMerchantId());
		info.setDate(rocInfo.getTransDate());
		info.setCardNumber(rocInfo.getCardNbr());
		info.setAuthorisationNumber(rocInfo.getAuthCode());
		info.setTransactionSales(sgdAmount);
		info.setMdrRate(mdrRate);
		info.setCommission(commission);
		info.setCreditAmount(creditAmount);
		info.setRemark(""); //leave blank
		info.setRrn(rocInfo.getRocText());
		return info;		
	}

}
