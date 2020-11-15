package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.DataStatus;

@Repository
public interface DataStatusRepo extends CrudRepository<DataStatus, Integer>{

}
