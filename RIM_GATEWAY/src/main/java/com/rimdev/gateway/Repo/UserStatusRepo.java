package com.rimdev.gateway.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.gateway.entities.UserStatus;

public interface UserStatusRepo extends CrudRepository<UserStatus, Integer>{
	


}
