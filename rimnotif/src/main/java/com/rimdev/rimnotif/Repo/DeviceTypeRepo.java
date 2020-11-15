package com.rimdev.rimnotif.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.DeviceOs;
import com.rimdev.rimnotif.entities.DeviceType;
@Repository
public interface DeviceTypeRepo extends CrudRepository<DeviceType, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.device_type where Dev_type =?1" , nativeQuery = true)
	Optional<DeviceType> findbyname(String name);

}
