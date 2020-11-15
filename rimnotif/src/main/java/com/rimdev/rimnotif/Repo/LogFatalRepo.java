package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.LogFatal;
@Repository
public interface LogFatalRepo extends CrudRepository<LogFatal, Integer>{

}
