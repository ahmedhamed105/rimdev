package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.UserType;
@Repository
public interface UserTypeRepo extends CrudRepository<UserType, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.user_type where user_type = ?1" , nativeQuery = true)
	Optional<UserType> findbytype(String type);

}
