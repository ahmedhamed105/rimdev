package com.rimdev.rimlang.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.Component;
import com.rimdev.rimlang.entities.MenuDisplay;


@Repository
public interface MenuDisplayRepo  extends CrudRepository<MenuDisplay, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.menu_display where Parent_menu_ID =?1 ORDER BY id ASC" , nativeQuery = true)
	Iterable<MenuDisplay> getbyparent(int parentid);

}

