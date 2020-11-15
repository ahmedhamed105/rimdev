package com.rimdev.rimlog.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlog.entities.LogFatal;
import com.rimdev.rimlog.entities.LogOther;
@Repository
public interface LogOtherRepo extends CrudRepository<LogOther, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.log_other where Error_code =?1" , nativeQuery = true)
	Optional<LogOther> findbycode(String code);

}
