package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.LogError;

@Repository
public interface LogErrorRepo extends CrudRepository<LogError, Integer>{

}
