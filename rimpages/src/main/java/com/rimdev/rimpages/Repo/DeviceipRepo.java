package com.rimdev.rimpages.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpages.entities.Deviceip;

@Repository
public interface DeviceipRepo extends CrudRepository<Deviceip, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.deviceip where ip_address=?1 AND DEVICE_ID =?2" , nativeQuery = true)
	Optional<Deviceip> findbyip(String ip,int deviceid);

}
