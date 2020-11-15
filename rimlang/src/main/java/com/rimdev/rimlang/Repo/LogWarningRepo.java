package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.LogWarning;
@Repository
public interface LogWarningRepo  extends CrudRepository<LogWarning, Integer>{

}
