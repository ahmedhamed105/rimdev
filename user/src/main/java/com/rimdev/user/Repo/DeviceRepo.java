package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.Device;

@Repository
public interface DeviceRepo extends CrudRepository<Device, Integer>{

}
