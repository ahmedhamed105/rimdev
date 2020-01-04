package com.rimdev.accounting.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Enttities.HoldProcess;

@Repository
public interface HoldProcessRepo extends CrudRepository<HoldProcess, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_accounting.hold_process where Hold_Status = ?1" , nativeQuery = true)
	Iterable<HoldProcess> findAllstatus(String status);
	
	@Query(value ="SELECT * FROM rim_accounting.hold_process where TRX_description like %?1%" , nativeQuery = true)
	Iterable<HoldProcess> findAllLikeDesc(String description);
	
	@Query(value ="SELECT * FROM rim_accounting.hold_process where Hold_Status = ?1 and TRX_description like %?2%" , nativeQuery = true)
	Iterable<HoldProcess> findAllstatusLikeDesc(String status,String description);
	
	
	@Query(value ="select * from rim_accounting.hold_process  where Hold_id = ?1 " , nativeQuery = true)
	Optional<HoldProcess> findbyholdid(Integer holdid);

}
