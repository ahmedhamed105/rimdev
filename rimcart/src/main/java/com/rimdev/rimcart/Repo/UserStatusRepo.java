package com.rimdev.rimcart.Repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimcart.entities.UserStatus;

public interface UserStatusRepo extends CrudRepository<UserStatus, Integer>{
	


}
