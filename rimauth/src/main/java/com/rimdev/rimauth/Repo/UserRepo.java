package com.rimdev.rimauth.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.User;
@Repository
public interface UserRepo extends CrudRepository<User, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.rimauth where first_name = ?1 and middle_name= ?2 and Last_name = ?3" , nativeQuery = true)
	Optional<User> findbyname(String firstname,String middlename,String lastname);
	

	
	
}
