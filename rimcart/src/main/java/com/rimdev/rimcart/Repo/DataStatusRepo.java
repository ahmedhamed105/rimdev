package com.rimdev.rimcart.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimcart.entities.DataStatus;

@Repository
public interface DataStatusRepo extends CrudRepository<DataStatus, Integer>{

}