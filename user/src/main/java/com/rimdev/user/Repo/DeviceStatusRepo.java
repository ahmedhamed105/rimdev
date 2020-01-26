package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.DeviceStatus;

@Repository
public interface DeviceStatusRepo extends CrudRepository<DeviceStatus, Integer>{

}
