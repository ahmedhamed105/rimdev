package com.rimdev.user.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rimdev.user.entities.ComponentFile;
import com.rimdev.user.entities.FileType;

@Repository
public interface ComponentFileRepo extends CrudRepository<ComponentFile, Integer>{

	@Query(value ="SELECT * FROM rim_user.component_file where Component_input_ID =?1" , nativeQuery = true)
	Iterable<ComponentFile> findbycompinput(int compinputid);
}
