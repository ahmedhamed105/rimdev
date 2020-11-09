package com.rimdev.rimdevices.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimdevices.entities.Component;
import com.rimdev.rimdevices.entities.ParentComponent;

public interface ParentComponentRepo extends CrudRepository<ParentComponent, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.parent_component where Pages_ID =?1" , nativeQuery = true)
	Iterable<ParentComponent> getbypage(int pageid);
}