package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rimdev.user.entities.UserFile;

@Repository
public interface UserFileRepo  extends CrudRepository<UserFile, Integer>{
	@Query(value ="SELECT * FROM rim_user.user_file  where User_ID = ?1" , nativeQuery = true)
	Iterable<UserFile> findbyuser(int userid);
	
	
	@Query(value ="SELECT * FROM rim_user.user_file  where files_upload_ID = ?1 AND User_ID = ?2 AND Component_ID = ?3" , nativeQuery = true)
	Optional<UserFile> findbyall(int files_upload_ID,int User_ID,int Component_ID);

}

