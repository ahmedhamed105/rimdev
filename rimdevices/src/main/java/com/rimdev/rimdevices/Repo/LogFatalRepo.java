package com.rimdev.rimdevices.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimdevices.entities.LogFatal;
@Repository
public interface LogFatalRepo extends CrudRepository<LogFatal, Integer>{

}
