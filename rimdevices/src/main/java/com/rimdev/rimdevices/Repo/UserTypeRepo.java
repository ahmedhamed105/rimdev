package com.rimdev.rimdevices.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimdevices.entities.Area;
import com.rimdev.rimdevices.entities.UserType;
@Repository
public interface UserTypeRepo extends CrudRepository<UserType, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.rimauth_type where user_type = ?1" , nativeQuery = true)
	Optional<UserType> findbytype(String type);

}
