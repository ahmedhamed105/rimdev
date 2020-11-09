package com.rimdev.rimpages.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.rimpages.entities.FilesUpload;
import com.rimdev.rimpages.entities.UserFile;

@Repository
public interface FilesUploadRepo  extends CrudRepository<FilesUpload, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.files_upload where files_name = ?1" , nativeQuery = true)
	Optional<FilesUpload> findbyfilename(String filename);
	
	@Query(value ="SELECT * FROM rim_user.files_upload where files_name = ?1 AND file_path = ?2" , nativeQuery = true)
	Optional<FilesUpload> findbyfile(String filename,String path);

}
