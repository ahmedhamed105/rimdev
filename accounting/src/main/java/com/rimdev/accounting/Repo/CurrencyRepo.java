package com.rimdev.accounting.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.Currency;


@Repository
public interface CurrencyRepo  extends CrudRepository<Currency, Integer>{

	@Query(value ="select * from rim_accounting.currency  where id = ?1 and Currency_status = 'Active'" , nativeQuery = true)
	Optional<Currency> findbyidstatus(Integer id);
	
	@Query(value ="select * from rim_accounting.currency  where Currency_status = ?1" , nativeQuery = true)
	Iterable<Currency> findAllstatus(String status);

}