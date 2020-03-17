package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.user.entities.UserStatus;

public interface UserStatusRepo extends CrudRepository<UserStatus, Integer>{
	


}
