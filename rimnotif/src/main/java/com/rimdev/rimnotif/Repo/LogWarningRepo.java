package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.LogWarning;
@Repository
public interface LogWarningRepo  extends CrudRepository<LogWarning, Integer>{

}
