package com.rimdev.rimcart.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimcart.entities.LogInfo;
@Repository
public interface LogInfoRepo extends CrudRepository<LogInfo, Integer>{

}
