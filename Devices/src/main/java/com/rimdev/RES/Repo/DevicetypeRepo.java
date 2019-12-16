package com.rimdev.RES.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.RES.entites.Device_type;

@Repository
public interface DevicetypeRepo extends CrudRepository<Device_type, Long>{

}
