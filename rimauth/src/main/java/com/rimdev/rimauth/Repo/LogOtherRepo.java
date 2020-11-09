package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.LogOther;
@Repository
public interface LogOtherRepo extends CrudRepository<LogOther, Integer>{

}
