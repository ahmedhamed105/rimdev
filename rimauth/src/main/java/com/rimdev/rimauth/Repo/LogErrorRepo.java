package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.LogError;

@Repository
public interface LogErrorRepo extends CrudRepository<LogError, Integer>{

}
