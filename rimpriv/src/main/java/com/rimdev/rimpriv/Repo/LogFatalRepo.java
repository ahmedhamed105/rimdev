package com.rimdev.rimpriv.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpriv.entities.LogFatal;
@Repository
public interface LogFatalRepo extends CrudRepository<LogFatal, Integer>{

}