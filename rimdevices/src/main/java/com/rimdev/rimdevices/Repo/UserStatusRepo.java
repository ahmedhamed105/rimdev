package com.rimdev.rimdevices.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimdevices.entities.UserStatus;

public interface UserStatusRepo extends CrudRepository<UserStatus, Integer>{
	


}
