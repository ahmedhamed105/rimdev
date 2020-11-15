package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.LogInfo;
@Repository
public interface LogInfoRepo extends CrudRepository<LogInfo, Integer>{

}
