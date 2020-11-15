package com.rimdev.rimlang.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.ComponentSelect;

@Repository
public interface ComponentSelectRepo  extends CrudRepository<ComponentSelect, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.component_select where Component_ID =?1" , nativeQuery = true)
	Iterable<ComponentSelect> getbycomponent(int compid);

}
