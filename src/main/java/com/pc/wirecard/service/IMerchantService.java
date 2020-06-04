package com.pc.wirecard.service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public interface IMerchantService {
	
	public void loadMerchantCommissionRateInMemory();
	public Map<String, BigDecimal> getMerchantCommissionRateMap();
	
}
