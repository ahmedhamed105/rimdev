package com.rimdev.rimcart.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rimdev.rimcart.entities.FileType;

@Repository
public interface FileTypeRepo extends CrudRepository<FileType, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.file_type where fmime =?1" , nativeQuery = true)
	Optional<FileType> findbymime(String mime);

}
