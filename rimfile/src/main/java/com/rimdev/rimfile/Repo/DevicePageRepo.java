package com.rimdev.rimfile.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimfile.entities.DevicePage;

@Repository
public interface DevicePageRepo  extends CrudRepository<DevicePage, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.device_page where DEVICE_ID = ?1" , nativeQuery = true)
	Iterable<DevicePage> findbydeviceid(int id);
	
	@Query(value ="SELECT * FROM rim_user.device_page where page_tokean =?1 AND  Pages_ID =?2" , nativeQuery = true)
	Optional<DevicePage> findbytokeanpage(String tokean,int pageid);
	
	


}
