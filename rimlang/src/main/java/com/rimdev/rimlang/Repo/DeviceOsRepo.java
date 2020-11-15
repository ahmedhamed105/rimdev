package com.rimdev.rimlang.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.DeviceOs;
@Repository
public interface DeviceOsRepo extends CrudRepository<DeviceOs, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.device_os where Device_OS=?1" , nativeQuery = true)
	Optional<DeviceOs> findbyname(String name);

}
