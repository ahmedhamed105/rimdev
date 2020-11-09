package com.rimdev.rimpages.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimpages.entities.ComponentButton;

public interface ComponentButtonRepo extends CrudRepository<ComponentButton, Integer> {

	
	@Query(value ="SELECT * FROM rim_user.component_button where Component_ID =?1" , nativeQuery = true)
	Iterable<ComponentButton> getbycomponent(int compid);
	
}