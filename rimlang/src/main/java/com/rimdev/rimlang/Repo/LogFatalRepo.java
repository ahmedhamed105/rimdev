package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.LogFatal;
@Repository
public interface LogFatalRepo extends CrudRepository<LogFatal, Integer>{

}
