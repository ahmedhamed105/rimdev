package com.rimdev.rimfile.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimfile.entities.Device;
import com.rimdev.rimfile.entities.DeviceOs;
import com.rimdev.rimfile.entities.DeviceType;

@Repository
public interface DeviceRepo extends CrudRepository<Device, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.device where Device_ip = ?1 and Device_OS_ID = ?2 and Device_type_ID = ?3 and Device_browser = ?4" , nativeQuery = true)
	Iterable<Device> findbyiposbrowser(String ip,DeviceOs os,DeviceType type,String browser);
	
	@Query(value ="SELECT * FROM rim_user.device where Device_code = ?1 " , nativeQuery = true)
	Optional<Device> findbydevicecode(String Device_code);

}
