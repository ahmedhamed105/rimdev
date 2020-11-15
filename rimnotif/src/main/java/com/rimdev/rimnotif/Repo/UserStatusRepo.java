package com.rimdev.rimnotif.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimnotif.entities.UserStatus;

public interface UserStatusRepo extends CrudRepository<UserStatus, Integer>{
	


}
