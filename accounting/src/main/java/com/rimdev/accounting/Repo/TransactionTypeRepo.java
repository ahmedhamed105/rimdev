package com.rimdev.accounting.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.TransactionType;


@Repository
public interface TransactionTypeRepo extends CrudRepository<TransactionType, Integer>{

}
