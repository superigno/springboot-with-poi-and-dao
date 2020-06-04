package com.pc.wirecard.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.wirecard.repository.MerchantRepository;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Service
public class MerchantService implements IMerchantService {

	private Map<String, BigDecimal> merchantCommissionRateMap = new HashMap<>();
	private static final Logger logger = LoggerFactory.getLogger(MerchantService.class);
	
	@Autowired
    private MerchantRepository repository;
	
	//@Autowired
	//private ModelMapper modelMapper;

	@Override
	public void loadMerchantCommissionRateInMemory() {
		final List<Object[]> list = (List<Object[]>) repository.find603MerchantCommissionRate();		
		list.forEach(obj -> {
			final String merchantId = ((String) obj[0]).substring(6);
			final String merchantCommissionRateString = (String) obj[1];
			BigDecimal merchantCommissionRateBigDecimal = new BigDecimal(0);			
			try {
				merchantCommissionRateBigDecimal = new BigDecimal(merchantCommissionRateString);
			} catch (Exception e) {
				logger.error("Error converting merchant commission rate for merchant id: {}. Defaulting to 0.", merchantId);
			}
			merchantCommissionRateMap.put(merchantId, merchantCommissionRateBigDecimal);	
			logger.trace("Reloaded rate info for merchant id: {}", merchantId);
		});
	}
	
	/* Future use
	public void loadMerchantInfo() {
		List<com.pc.wirecard.model.entity.MerchantInfo> list = (List<MerchantInfo>) repository.findWhereStatusIsZero();
		for (com.pc.wirecard.model.entity.MerchantInfo info : list) {
			final MerchantInfo infoDto = modelMapper.map(info, MerchantInfo.class); //Need to use dto ideally
			try {
				final String merchantId = info.getBankMerchantId().substring(6);
				merchantInfoMap.put(merchantId, info);	
				logger.trace("Reloaded info for merchant id: {}", merchantId);
			} catch (Exception e) {
				logger.error("Error getting merchantId; skipped {}", info);
			}
		}
	}
	*/	

	@Override
	public Map<String, BigDecimal> getMerchantCommissionRateMap() {
		return merchantCommissionRateMap;
	}
	
}
