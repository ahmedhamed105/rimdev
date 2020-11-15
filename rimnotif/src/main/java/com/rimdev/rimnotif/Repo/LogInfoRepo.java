package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.LogInfo;
@Repository
public interface LogInfoRepo extends CrudRepository<LogInfo, Integer>{

}
