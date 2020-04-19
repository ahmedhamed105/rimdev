package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.LoginType;
@Repository
public interface LoginTypeRepo  extends CrudRepository<LoginType, Integer> {
	
	
	@Query(value ="SELECT * FROM rim_user.login_type where Ltype=?1" , nativeQuery = true)
	Optional<LoginType> findbyname(String name);

}
