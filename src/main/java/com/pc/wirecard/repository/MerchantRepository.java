package com.pc.wirecard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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

	@Query("SELECT m FROM MerchantInfo m WHERE m.status = 0")
	List<MerchantInfo> findWhereStatusIsZero();

	// bank_merchant_id should essentially be 000603xxxxxxxxx format, if lesser than that (future bank_merchant_ids), use padding for it to be still valid
	@Query(value = "SELECT DISTINCT LPAD(bank_merchant_id, 15, \"000603000000000\"), dcc_commission FROM merchant WHERE status = 0 AND LEFT(LPAD(bank_merchant_id, 15, \"000603000000000\"), 6) = '000603'", nativeQuery = true)
	public List<Object[]> find603MerchantCommissionRate();

}
