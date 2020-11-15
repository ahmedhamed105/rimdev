package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.LogError;

@Repository
public interface LogErrorRepo extends CrudRepository<LogError, Integer>{

}
