package com.rimdev.rimlang.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlang.entities.RelationType;

@Repository
public interface RelationTypeRepo  extends CrudRepository<RelationType, Integer>{

}

