package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.LogFatal;
@Repository
public interface LogFatalRepo extends CrudRepository<LogFatal, Integer>{

}
