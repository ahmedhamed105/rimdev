package com.rimdev.rimpages.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpages.entities.Configuration;

@Repository
public interface ConfigurationRepo  extends CrudRepository<Configuration, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.configuration where Config_key=?1" , nativeQuery = true)
	Optional<Configuration> findbykey(String key);
	
	@Query(value ="SELECT * FROM rim_user.configuration where Config_key=?1" , nativeQuery = true)
	Iterable<Configuration> findallbykey(String key);

}

