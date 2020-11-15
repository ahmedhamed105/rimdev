package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.ParentMenu;

@Repository
public interface ParentMenuRepo  extends CrudRepository<ParentMenu, Integer>{

}

