package com.rimdev.rimpages.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpages.entities.LogType;
@Repository
public interface LogTypeRepo extends CrudRepository<LogType, Integer>{

}
