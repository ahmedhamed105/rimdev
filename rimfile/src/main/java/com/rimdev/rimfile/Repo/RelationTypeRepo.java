package com.rimdev.rimfile.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimfile.entities.RelationType;

@Repository
public interface RelationTypeRepo  extends CrudRepository<RelationType, Integer>{

}

