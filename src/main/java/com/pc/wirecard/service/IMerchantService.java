package com.pc.wirecard.service;

import java.util.Map;

import com.pc.wirecard.model.entity.MerchantInfo;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
public interface IMerchantService {
	
	public void loadInMemory();
	public Map<String, MerchantInfo> getMerchantInfo();
	
}
