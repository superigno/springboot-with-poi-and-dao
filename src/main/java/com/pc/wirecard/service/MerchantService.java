package com.pc.wirecard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.wirecard.model.entity.MerchantInfo;
import com.pc.wirecard.repository.MerchantRepository;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Service
public class MerchantService implements IMerchantService {

	private Map<String, MerchantInfo> merchantInfoMap = new HashMap<String, MerchantInfo>();
	private static final Logger logger = LoggerFactory.getLogger(MerchantService.class);
	
	@Autowired
    private MerchantRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void loadInMemory() {
		List<com.pc.wirecard.model.entity.MerchantInfo> list = (List<MerchantInfo>) repository.findAll();
		for (com.pc.wirecard.model.entity.MerchantInfo info : list) {
			final MerchantInfo infoDto = modelMapper.map(info, MerchantInfo.class);
			final String merchantId = infoDto.getBankMerchantId().substring(6);
			merchantInfoMap.put(merchantId, infoDto);	
			logger.trace("Reloaded info for merchant id: {}", merchantId);
		}
	}

	@Override
	public Map<String, MerchantInfo> getMerchantInfo() {
		return merchantInfoMap;
	}

	
}
