package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.ParentMenu;

@Repository
public interface ParentMenuRepo  extends CrudRepository<ParentMenu, Integer>{

}

