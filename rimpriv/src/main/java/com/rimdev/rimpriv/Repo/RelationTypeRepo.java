package com.rimdev.rimpriv.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpriv.entities.RelationType;

@Repository
public interface RelationTypeRepo  extends CrudRepository<RelationType, Integer>{

}
