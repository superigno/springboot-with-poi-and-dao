package com.pc.wirecard;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pc.wirecard.model.entity.MerchantInfo;
import com.pc.wirecard.repository.MerchantRepository;
import com.pc.wirecard.service.IMerchantService;

@SpringBootTest
class MerchantServiceTest {
	
	@Autowired
    private MerchantRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private IMerchantService merchantService;
	
	@Test
	void testFindMerchantCommission() {
		
		List<Object[]> list = (List<Object[]>) repository.find603MerchantCommission();
		list.stream().forEach(obj -> {			
			System.out.println(obj[0] + " "+obj[1] );			
		});
	}
	
	
	@Test
	void testFromDb() {
		List<MerchantInfo> list = (List<MerchantInfo>) repository.findAll();
		
		System.out.println("Size: "+list.size());
		assertTrue(list.size() > 0);
		
		list.forEach(item -> {
			com.pc.wirecard.model.MerchantInfo infoDto = modelMapper.map(item, com.pc.wirecard.model.MerchantInfo.class);
			System.out.println(infoDto.getName()+" | "+infoDto.getDisplayTradingName());
		});
	}
	
	@Test
	void testInMemory() {
		merchantService.loadMerchantCommissionRateInMemory();
		Map<String, BigDecimal> map = merchantService.getMerchantCommissionRateMap();
		System.out.println("Size: "+map.size());
		assertTrue(map.size() > 0);
		
		map.forEach((key, value) -> System.out.println(key + ":" + value));
		
	}

}
