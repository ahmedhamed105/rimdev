package com.rimdev.rimauth.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimauth.entities.UserStatus;

public interface UserStatusRepo extends CrudRepository<UserStatus, Integer>{
	


}
