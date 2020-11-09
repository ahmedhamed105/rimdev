package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.LogWarning;
@Repository
public interface LogWarningRepo  extends CrudRepository<LogWarning, Integer>{

}
