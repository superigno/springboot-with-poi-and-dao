package com.pc.wirecard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pc.wirecard.model.entity.MerchantInfo;

/**
 * @author gino.q
 * @date June 1, 2020
 *
 */
@Repository
public interface MerchantRepository extends CrudRepository<MerchantInfo, Long> {

}
