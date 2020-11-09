package com.rimdev.rimdevices.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimdevices.entities.DataStatus;

@Repository
public interface DataStatusRepo extends CrudRepository<DataStatus, Integer>{

}
