package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.Area;


@Repository
public interface AreaRepo extends CrudRepository<Area, Integer>{

}
