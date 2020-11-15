package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.ParentMenu;

@Repository
public interface ParentMenuRepo  extends CrudRepository<ParentMenu, Integer>{

}

