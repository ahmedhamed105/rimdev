package com.rimdev.rimlang.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.Pages;
import com.rimdev.rimlang.entities.Telephones;


@Repository
public interface PagesRepo extends CrudRepository<Pages, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.pages where id =?1" , nativeQuery = true)
	Optional<Pages> findbyid(int id);
	
	@Query(value ="SELECT * FROM rim_user.pages where Page_name =?1" , nativeQuery = true)
	Optional<Pages> findbypagename(String pagename);
	
	
}
