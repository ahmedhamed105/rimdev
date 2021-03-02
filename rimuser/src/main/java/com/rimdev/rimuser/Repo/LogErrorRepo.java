package com.rimdev.rimuser.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimuser.entities.LogError;

@Repository
public interface LogErrorRepo extends CrudRepository<LogError, Integer>{

}