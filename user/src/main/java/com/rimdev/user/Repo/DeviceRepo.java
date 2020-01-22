package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.Device;

@Repository
public interface DeviceRepo extends CrudRepository<Device, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.device where Device_name = ?1" , nativeQuery = true)
	Iterable<Device> findbyname(String name);

}
