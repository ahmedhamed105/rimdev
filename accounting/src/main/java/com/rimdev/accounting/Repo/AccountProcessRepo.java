package com.rimdev.accounting.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.AccountProcess;


@Repository
public interface AccountProcessRepo extends CrudRepository<AccountProcess, Integer>{



}
