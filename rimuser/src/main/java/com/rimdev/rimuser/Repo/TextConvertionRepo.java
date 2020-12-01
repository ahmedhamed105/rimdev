package com.rimdev.rimuser.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimuser.entities.TextConvertion;


@Repository
public interface TextConvertionRepo extends CrudRepository<TextConvertion, Integer> {
	
	@Query(value ="SELECT * FROM rim_language.text_convertion where language_map_ID = ?1" , nativeQuery = true)
	Iterable<TextConvertion> getbymap(int language_map_ID);
	
	@Query(value ="SELECT * FROM rim_language.text_convertion where Languages_ID = ?1 AND language_map_ID = ?2" , nativeQuery = true)
	Optional<TextConvertion> getbylangandmap(int Languages_ID,int language_map_ID);
	
	
	@Query(value ="SELECT * FROM rim_language.text_convertion where language_map_ID = ?1" , nativeQuery = true)
	Iterable<TextConvertion> getbymapid(int language_map_ID);
	
	
	@Query(value ="SELECT t.* FROM rim_language.text_convertion t,rim_language.language_map m where t.Languages_ID = ?1 AND t.language_map_ID = m.ID AND m.Text_code = ?2" , nativeQuery = true)
	Optional<TextConvertion> getbylangandmaptxt(int Languages_ID,String code);

}