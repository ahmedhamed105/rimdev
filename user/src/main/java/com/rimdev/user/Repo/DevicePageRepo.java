package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.DevicePage;

@Repository
public interface DevicePageRepo  extends CrudRepository<DevicePage, Integer>{

}
