package com.rimdev.accounting.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.Account;

@Repository
public interface  AccountRepo extends CrudRepository<Account, Integer>{
	
	
	@Query(value ="select a.* from rim_accounting.Account a,rim_accounting.currency c  where a.id = ?1 and a.Currency_ID = c.id and c.Currency_status = 'Active' and a.Acct_status = 'Active'" , nativeQuery = true)
	Optional<Account> findbyidstatus(Integer id);
	
	
	@Query(value ="select a.* from rim_accounting.Account a,rim_accounting.currency c  where a.Currency_ID = c.id and c.Currency_status = 'Active' and a.Acct_status = ?1" , nativeQuery = true)
	Iterable<Account> findAllstatus(String status);
	

}
