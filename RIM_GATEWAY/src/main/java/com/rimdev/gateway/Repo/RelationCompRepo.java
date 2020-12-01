package com.rimdev.gateway.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.gateway.entities.ComponentSelect;
import com.rimdev.gateway.entities.RelationComp;

@Repository
public interface RelationCompRepo  extends CrudRepository<RelationComp, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.relation_comp where Component_ID = ?1" , nativeQuery = true)
	Iterable<RelationComp> getbycomponent(int compid);

}
