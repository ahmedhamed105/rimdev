package com.rimdev.accounting.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.Currency;


@Repository
public interface CurrencyRepo  extends CrudRepository<Currency, Integer>{



}