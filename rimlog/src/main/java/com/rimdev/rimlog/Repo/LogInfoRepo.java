package com.rimdev.rimlog.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimlog.entities.LogError;
import com.rimdev.rimlog.entities.LogInfo;
@Repository
public interface LogInfoRepo extends CrudRepository<LogInfo, Integer>{
	
	@Query(value ="SELECT * FROM rim_user.log_info where Error_code =?1" , nativeQuery = true)
	Optional<LogInfo> findbycode(String code);

}
