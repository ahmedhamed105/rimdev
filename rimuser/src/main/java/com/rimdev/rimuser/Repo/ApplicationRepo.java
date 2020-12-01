package com.rimdev.rimuser.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimuser.entities.Application;

@Repository
public interface ApplicationRepo  extends CrudRepository<Application, Integer> {
	
	
	@Query(value ="SELECT * FROM rim_user.application where App_name=?1" , nativeQuery = true)
	Optional<Application> findbyname(String name);

}
