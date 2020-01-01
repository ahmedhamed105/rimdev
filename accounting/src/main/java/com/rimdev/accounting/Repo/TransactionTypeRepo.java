package com.rimdev.accounting.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Enttities.TransactionType;


@Repository
public interface TransactionTypeRepo extends CrudRepository<TransactionType, Integer>{

	@Query(value ="select * from rim_accounting.transaction_type   where id = ?1 and TRXtype_status = 'Active' " , nativeQuery = true)
	Optional<TransactionType> findbyidstatus(Integer id);
	
	@Query(value ="select * from rim_accounting.transaction_type   where TRXtype_status = ?1" , nativeQuery = true)
	Iterable<TransactionType> findAllstatus(String status);
	
	
	@Query(value ="select * from rim_accounting.transaction_type   where payment_not = ?1" , nativeQuery = true)
	Iterable<TransactionType> findAllpaymnet(int pay);
	
}
