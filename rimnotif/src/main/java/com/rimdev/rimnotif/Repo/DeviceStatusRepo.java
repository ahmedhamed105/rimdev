package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.DeviceStatus;

@Repository
public interface DeviceStatusRepo extends CrudRepository<DeviceStatus, Integer>{

}
