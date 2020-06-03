package com.pc.wirecard;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
		merchantService.loadInMemory();
		Map<String, MerchantInfo> map = merchantService.getMerchantInfo();
		System.out.println("Size: "+map.size());
		assertTrue(map.size() > 0);
		
		map.forEach((key, value) -> System.out.println(key + ":" + value.getName()+" | "+value.getDisplayTradingName()));
		
	}

}
