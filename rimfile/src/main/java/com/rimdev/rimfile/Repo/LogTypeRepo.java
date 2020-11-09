package com.rimdev.rimfile.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimfile.entities.LogType;
@Repository
public interface LogTypeRepo extends CrudRepository<LogType, Integer>{

}
