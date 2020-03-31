package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.UserLogin;


@Repository
public interface UserLoginRepo extends CrudRepository<UserLogin, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.user_login where User_ID =?1" , nativeQuery = true)
	Iterable<UserLogin> findbyuser(int userid);
	
	@Query(value ="SELECT * FROM rim_user.user_login where Username =?1" , nativeQuery = true)
	Optional<UserLogin> findbyusername(String username);
	
	@Query(value ="SELECT * FROM rim_user.user_login where Username =?1 AND User_tokean =?2" , nativeQuery = true)
	Optional<UserLogin> findbyusernametokean(String username,String tokean);

}
