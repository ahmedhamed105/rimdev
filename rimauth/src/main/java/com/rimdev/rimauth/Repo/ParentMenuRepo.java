package com.rimdev.rimauth.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimauth.entities.ParentMenu;

@Repository
public interface ParentMenuRepo  extends CrudRepository<ParentMenu, Integer>{

}

