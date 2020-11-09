package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.DataStatus;

@Repository
public interface DataStatusRepo extends CrudRepository<DataStatus, Integer>{

}
