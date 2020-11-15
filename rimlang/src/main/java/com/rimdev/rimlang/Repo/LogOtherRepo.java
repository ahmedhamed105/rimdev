package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.LogOther;
@Repository
public interface LogOtherRepo extends CrudRepository<LogOther, Integer>{

}
