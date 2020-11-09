package com.rimdev.rimcart.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimcart.entities.LogOther;
@Repository
public interface LogOtherRepo extends CrudRepository<LogOther, Integer>{

}
