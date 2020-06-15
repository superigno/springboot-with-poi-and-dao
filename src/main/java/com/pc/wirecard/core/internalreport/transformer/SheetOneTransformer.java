package com.pc.wirecard.core.internalreport.transformer;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.pc.wirecard.constant.WirecardConstants;
import com.pc.wirecard.core.ITransform;
import com.pc.wirecard.model.internalreport.SheetOneInfo;
import com.pc.wirecard.model.poiji.RoctextInfo;
import com.pc.wirecard.util.WirecardUtils;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Component("internalSheetOneTransformer")
public class SheetOneTransformer implements ITransform<SheetOneInfo, RoctextInfo>{
	
	public List<SheetOneInfo> transform(List<RoctextInfo> list) {		
		List<SheetOneInfo> cellInfoList = list.stream().map(this::map).collect(Collectors.toList());
		return cellInfoList;
	}
	
	private SheetOneInfo map(RoctextInfo rocInfo) {
		final SheetOneInfo internalInfo = new SheetOneInfo();
		final boolean isRefund = WirecardConstants.LOGIC_MODULE_REFUND.equals(rocInfo.getLogicModule());
		BigDecimal sgdAmount = WirecardConstants.CURRENCY_BASE.equals(rocInfo.getCcy()) ? BigDecimal.ZERO : rocInfo.getBaseAmt(); //should be ZERO on local transactions in internal report
		BigDecimal transAmt = rocInfo.getTransAmt();
		BigDecimal submitAmt = rocInfo.getSubmitAmt();
		BigDecimal oGrossAmt = rocInfo.getoGrossAmt();
		BigDecimal oComAmt = rocInfo.getoComAmt();
		BigDecimal oNetAmt = rocInfo.getoNetAmt();
		BigDecimal sgdPayment = WirecardUtils.getSgdPayment(oComAmt, oGrossAmt, sgdAmount);
		
		if (isRefund) {
			sgdAmount = WirecardUtils.returnAsNegative(sgdAmount);
			sgdPayment = WirecardUtils.returnAsNegative(sgdPayment);		
			transAmt = WirecardUtils.returnAsNegative(transAmt);
			submitAmt = WirecardUtils.returnAsNegative(submitAmt);
			oGrossAmt = WirecardUtils.returnAsNegative(oGrossAmt);
			oComAmt = WirecardUtils.returnAsNegative(oComAmt);
			oNetAmt = WirecardUtils.returnAsNegative(oNetAmt);		
		}
		
		internalInfo.setOrg(rocInfo.getOrg());		
		internalInfo.setMerchantId(rocInfo.getMerchantId());
		internalInfo.setMerchantName(rocInfo.getMerchantName());
		internalInfo.setTerminalId(rocInfo.getTerminalId());
		internalInfo.setBatchSocNbr(rocInfo.getBatchSocNbr());
		internalInfo.setRefBatchSrce(rocInfo.getRefBatchSrce());
		internalInfo.setCardNbr(rocInfo.getCardNbr());
		internalInfo.setTransDate(rocInfo.getTransDate());
		internalInfo.setCcy(rocInfo.getCcy());
		internalInfo.setTransAmt(transAmt);
		internalInfo.setAuthCode(rocInfo.getAuthCode());
		internalInfo.setCardType(rocInfo.getCardType());
		internalInfo.setOnUsOffUsFlag(rocInfo.getOnUsOffUsFlag());
		internalInfo.setProduct(rocInfo.getProduct());
		internalInfo.setAcqRefNum(rocInfo.getAcqRefNum());
		internalInfo.setDescription(rocInfo.getDescription());
		internalInfo.setPostDate(rocInfo.getPostDate());
		internalInfo.setSubmitCcy(rocInfo.getSubmitCcy());
		internalInfo.setSubmitAmt(submitAmt);
		internalInfo.setSubMedia(rocInfo.getSubMedia());
		internalInfo.setLogicModule(rocInfo.getLogicModule());
		internalInfo.setQualIndicator(rocInfo.getQualIndicator());
		internalInfo.setDataEntryMode(rocInfo.getDataEntryMode());
		internalInfo.setMcc(rocInfo.getMcc());
		internalInfo.setGapOsMode(rocInfo.getGapOsMode());
		internalInfo.setoGrossAmt(oGrossAmt);
		internalInfo.setoComAmt(oComAmt);
		internalInfo.setoVatAmt(rocInfo.getoVatAmt());
		internalInfo.setoOthAmt(rocInfo.getoOthAmt());
		internalInfo.setoNetAmt(oNetAmt);
		internalInfo.setRocText(rocInfo.getRocText());
		internalInfo.setSgdAmount(WirecardUtils.roundUpTwoDecimalPlaces(sgdAmount));
		internalInfo.setSgdPayment(WirecardUtils.roundUpTwoDecimalPlaces(sgdPayment));
		internalInfo.setExceptionSgdAmount(BigDecimal.ZERO);
		return internalInfo;		
	}
}
