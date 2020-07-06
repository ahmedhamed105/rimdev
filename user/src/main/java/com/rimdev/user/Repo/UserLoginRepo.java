package com.rimdev.user.Repo;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rimdev.user.entities.UserLogin;


@Repository
public interface UserLoginRepo extends CrudRepository<UserLogin, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.user_login where User_ID =?1" , nativeQuery = true)
	Iterable<UserLogin> findbyuser(int userid);
	
	@Query(value ="SELECT * FROM rim_user.user_login where Username =?1" , nativeQuery = true)
	Optional<UserLogin> findbyusername(String username);
	
	@Query(value ="SELECT * FROM rim_user.user_login where Username =?1 AND User_tokean =?2" , nativeQuery = true)
	Optional<UserLogin> findbyusernametokean(String username,String tokean);
	
	
	@Query(value ="SELECT a.* FROM rim_user.user_login a,rim_user.user b where a.User_ID = b.ID and b.User_id_number =?1" , nativeQuery = true)
	Iterable<UserLogin> findbyuserid(String userid);

}
