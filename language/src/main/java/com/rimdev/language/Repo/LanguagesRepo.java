package com.rimdev.language.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.rimdev.language.Entities.Languages;

public interface LanguagesRepo extends CrudRepository<Languages, Integer> {
	
	
	@Query(value ="SELECT * FROM rim_language.languages where Language_code = ?1" , nativeQuery = true)
	Optional<Languages> getbycode(String code);

}
