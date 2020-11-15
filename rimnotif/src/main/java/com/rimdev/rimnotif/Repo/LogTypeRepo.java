package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.LogType;
@Repository
public interface LogTypeRepo extends CrudRepository<LogType, Integer>{

}
