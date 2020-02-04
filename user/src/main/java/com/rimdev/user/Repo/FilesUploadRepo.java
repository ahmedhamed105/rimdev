package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.entities.UserFile;

@Repository
public interface FilesUploadRepo  extends CrudRepository<FilesUpload, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.files_upload where files_name = ?1" , nativeQuery = true)
	Optional<FilesUpload> findbyfilename(String filename);

}
