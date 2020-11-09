package com.rimdev.rimfile.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimfile.entities.ComponentSelect;
import com.rimdev.rimfile.entities.RelationComp;

@Repository
public interface RelationCompRepo  extends CrudRepository<RelationComp, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.relation_comp where Component_ID = ?1" , nativeQuery = true)
	Iterable<RelationComp> getbycomponent(int compid);

}
