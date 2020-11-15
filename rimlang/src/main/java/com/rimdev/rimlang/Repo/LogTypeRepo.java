package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.LogType;
@Repository
public interface LogTypeRepo extends CrudRepository<LogType, Integer>{

}
