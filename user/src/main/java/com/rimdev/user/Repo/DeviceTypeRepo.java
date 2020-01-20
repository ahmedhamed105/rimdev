package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.DeviceType;
@Repository
public interface DeviceTypeRepo extends CrudRepository<DeviceType, Integer>{

}
