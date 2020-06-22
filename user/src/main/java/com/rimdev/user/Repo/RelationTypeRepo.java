package com.rimdev.user.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.RelationType;

@Repository
public interface RelationTypeRepo  extends CrudRepository<RelationType, Integer>{

}

