package com.rimdev.rimcart.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.rimdev.rimcart.entities.ComponentInput;
import com.rimdev.rimcart.entities.ComponentSelect;

public interface ComponentInputRepo extends CrudRepository<ComponentInput, Integer> {

	
	@Query(value ="SELECT * FROM rim_user.component_input where Component_ID =?1" , nativeQuery = true)
	Iterable<ComponentInput> getbycomponent(int compid);
}
