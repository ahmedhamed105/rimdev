package com.rimdev.rimpages.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpages.entities.LogWarning;
@Repository
public interface LogWarningRepo  extends CrudRepository<LogWarning, Integer>{

}
