package com.rimdev.product.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.product.entities.TextConvertion;



@Repository
public interface TextConvertionRepo extends CrudRepository<TextConvertion, Integer> {
	
	
	@Query(value ="SELECT * FROM rim_language.text_convertion where Languages_ID = ?1 AND language_map_ID = ?2" , nativeQuery = true)
	Optional<TextConvertion> getbylangandmap(int Languages_ID,int language_map_ID);

}