package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;

import com.rimdev.user.entities.GroupStatus;

public interface GroupStatusRepo extends CrudRepository<GroupStatus, Integer>{
	
	
}
