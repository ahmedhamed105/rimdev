package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.UserFile;

@Repository
public interface UserFileRepo  extends CrudRepository<UserFile, Integer>{
	@Query(value ="SELECT * FROM rim_user.user_file  where User_ID = ?1 and file_App_type_ID = ?2" , nativeQuery = true)
	Iterable<UserFile> findbyusertype(int userid,int filetype);
	
	
	@Query(value ="SELECT * FROM rim_user.user_file  where User_ID = ?1 and file_App_type_ID = ?2 and  files_upload_ID= ?3 " , nativeQuery = true)
	Optional<UserFile> findbyusertypefile(int userid,int filetype,int fileid);
}

