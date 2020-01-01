package com.rimdev.accounting.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.FlowType;


@Repository
public interface FlowTypeRepo extends CrudRepository<FlowType, Integer>{
	
	@Query(value ="select * from rim_accounting.flow_type  where id = ?1 and Flow_status = 'Active'" , nativeQuery = true)
	Optional<FlowType> findbyidstatus(Integer id);
	
	@Query(value ="select * from rim_accounting.flow_type  where Flow_status = ?1" , nativeQuery = true)
	Iterable<FlowType> findAllstatus(String status);
	
	

}
