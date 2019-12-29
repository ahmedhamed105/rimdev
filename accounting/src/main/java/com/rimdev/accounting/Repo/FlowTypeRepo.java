package com.rimdev.accounting.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.accounting.Enttities.FlowType;


@Repository
public interface FlowTypeRepo extends CrudRepository<FlowType, Integer>{

}
