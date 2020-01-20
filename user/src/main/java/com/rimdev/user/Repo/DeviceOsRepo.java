package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.DeviceOs;
@Repository
public interface DeviceOsRepo extends CrudRepository<DeviceOs, Integer>{

}
