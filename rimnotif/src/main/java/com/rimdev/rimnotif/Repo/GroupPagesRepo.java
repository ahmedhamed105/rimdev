package com.rimdev.rimnotif.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimnotif.entities.GroupPages;

public interface GroupPagesRepo extends CrudRepository<GroupPages, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.group_pages where Group_priviledge_ID =?1 " , nativeQuery = true)
	Iterable<GroupPages> getbygroup(int groupid);

}
