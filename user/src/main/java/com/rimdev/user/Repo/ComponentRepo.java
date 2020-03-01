package com.rimdev.user.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rimdev.user.entities.Component;


@Repository
public interface ComponentRepo extends CrudRepository<Component, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.component where Pages_ID =?1" , nativeQuery = true)
	Iterable<Component> getbypage(int pageid);

}
