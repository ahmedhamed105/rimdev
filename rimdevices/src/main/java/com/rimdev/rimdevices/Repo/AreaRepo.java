package com.rimdev.rimdevices.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimdevices.entities.Area;
import com.rimdev.rimdevices.entities.Email;


@Repository
public interface AreaRepo extends CrudRepository<Area, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.area where Area_name = ?1" , nativeQuery = true)
	Optional<Area> findbyarea(String area);

}