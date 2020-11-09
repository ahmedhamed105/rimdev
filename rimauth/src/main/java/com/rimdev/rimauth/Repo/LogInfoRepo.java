package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.LogInfo;
@Repository
public interface LogInfoRepo extends CrudRepository<LogInfo, Integer>{

}
