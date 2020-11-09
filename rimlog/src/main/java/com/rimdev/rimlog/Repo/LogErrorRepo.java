package com.rimdev.rimlog.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlog.entities.LogError;

@Repository
public interface LogErrorRepo extends CrudRepository<LogError, Integer>{

}
