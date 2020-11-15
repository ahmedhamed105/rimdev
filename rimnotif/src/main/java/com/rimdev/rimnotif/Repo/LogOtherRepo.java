package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.LogOther;
@Repository
public interface LogOtherRepo extends CrudRepository<LogOther, Integer>{

}
