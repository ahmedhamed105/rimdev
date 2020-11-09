package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.DeviceStatus;

@Repository
public interface DeviceStatusRepo extends CrudRepository<DeviceStatus, Integer>{

}
