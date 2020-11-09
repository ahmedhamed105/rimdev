package com.rimdev.rimlog.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlog.entities.ParentMenu;

@Repository
public interface ParentMenuRepo  extends CrudRepository<ParentMenu, Integer>{

}

