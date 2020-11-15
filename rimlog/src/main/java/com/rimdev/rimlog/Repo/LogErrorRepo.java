package com.rimdev.rimlog.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlog.entities.LogError;

@Repository
public interface LogErrorRepo extends CrudRepository<LogError, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.log_error where Error_code =?1" , nativeQuery = true)
	Optional<LogError> findbycode(String code);

}
