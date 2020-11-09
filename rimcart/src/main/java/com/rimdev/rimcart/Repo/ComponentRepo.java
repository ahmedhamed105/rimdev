package com.rimdev.rimcart.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rimdev.rimcart.entities.Component;


@Repository
public interface ComponentRepo extends CrudRepository<Component, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.component where parent_component_ID =?1 ORDER BY seq_num ASC" , nativeQuery = true)
	Iterable<Component> getbyparent(int parentid);

	
}
