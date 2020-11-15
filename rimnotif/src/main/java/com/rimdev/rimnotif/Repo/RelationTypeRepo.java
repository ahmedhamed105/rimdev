package com.rimdev.rimnotif.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimnotif.entities.RelationType;

@Repository
public interface RelationTypeRepo  extends CrudRepository<RelationType, Integer>{

}

