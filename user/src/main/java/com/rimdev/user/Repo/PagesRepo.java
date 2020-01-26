package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.Pages;


@Repository
public interface PagesRepo extends CrudRepository<Pages, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.pages where id =?1" , nativeQuery = true)
	Optional<Pages> findbyid(int id);
	
}
