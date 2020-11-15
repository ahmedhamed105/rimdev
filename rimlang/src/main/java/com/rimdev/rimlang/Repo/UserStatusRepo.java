package com.rimdev.rimlang.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimlang.entities.UserStatus;

public interface UserStatusRepo extends CrudRepository<UserStatus, Integer>{
	


}
