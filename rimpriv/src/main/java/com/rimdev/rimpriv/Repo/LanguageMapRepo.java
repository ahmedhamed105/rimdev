package com.rimdev.rimpriv.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpriv.entities.LanguageMap;


@Repository
public interface LanguageMapRepo  extends CrudRepository<LanguageMap, Integer> {
	
	

	@Query(value ="SELECT * FROM rim_language.language_map where Text_code = ?1" , nativeQuery = true)
	Optional<LanguageMap> getbycode(String code);


}
