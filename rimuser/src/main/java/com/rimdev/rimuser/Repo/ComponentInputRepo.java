package com.rimdev.rimuser.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimuser.entities.ComponentInput;
import com.rimdev.rimuser.entities.ComponentSelect;

public interface ComponentInputRepo extends CrudRepository<ComponentInput, Integer> {

	
	@Query(value ="SELECT * FROM rim_user.component_input where Component_ID =?1" , nativeQuery = true)
	Iterable<ComponentInput> getbycomponent(int compid);
}
