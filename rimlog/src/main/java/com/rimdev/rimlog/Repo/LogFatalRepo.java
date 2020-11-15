package com.rimdev.rimlog.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlog.entities.LogFatal;
import com.rimdev.rimlog.entities.LogWarning;
@Repository
public interface LogFatalRepo extends CrudRepository<LogFatal, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.log_fatal where Error_code =?1" , nativeQuery = true)
	Optional<LogFatal> findbycode(String code);

}
