package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.Email;
@Repository
public interface EmailRepo extends CrudRepository<Email, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.email where Email_user =?1" , nativeQuery = true)
	Optional<Email> findbyemail(String email);

}
