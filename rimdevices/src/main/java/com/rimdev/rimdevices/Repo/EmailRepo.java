package com.rimdev.rimdevices.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimdevices.entities.Email;
@Repository
public interface EmailRepo extends CrudRepository<Email, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.email where Email_user =?1" , nativeQuery = true)
	Optional<Email> findbyemail(String email);
	
	
	@Query(value ="SELECT * FROM rim_user.email where User_login_ID =?1" , nativeQuery = true)
	Iterable<Email> findbyuser(int userid);

}
