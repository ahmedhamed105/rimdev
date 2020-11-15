package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.DeviceStatus;

@Repository
public interface DeviceStatusRepo extends CrudRepository<DeviceStatus, Integer>{

}
