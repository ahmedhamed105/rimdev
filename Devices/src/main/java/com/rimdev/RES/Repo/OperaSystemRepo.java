package com.rimdev.RES.Repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.RES.entites.OperaSystem;



@Repository
public interface OperaSystemRepo extends CrudRepository<OperaSystem, Long> {

}
