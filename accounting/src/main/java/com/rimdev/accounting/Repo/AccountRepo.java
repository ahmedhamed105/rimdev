package com.rimdev.accounting.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.Account;

@Repository
public interface  AccountRepo extends CrudRepository<Account, Integer>{

}
