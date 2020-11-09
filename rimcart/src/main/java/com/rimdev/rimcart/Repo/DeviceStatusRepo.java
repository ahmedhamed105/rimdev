package com.rimdev.rimcart.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimcart.entities.DeviceStatus;

@Repository
public interface DeviceStatusRepo extends CrudRepository<DeviceStatus, Integer>{

}
