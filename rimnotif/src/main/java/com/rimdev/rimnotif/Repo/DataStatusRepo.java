package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.DataStatus;

@Repository
public interface DataStatusRepo extends CrudRepository<DataStatus, Integer>{

}
