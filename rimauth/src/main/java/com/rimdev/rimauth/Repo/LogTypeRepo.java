package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.LogType;
@Repository
public interface LogTypeRepo extends CrudRepository<LogType, Integer>{

}
