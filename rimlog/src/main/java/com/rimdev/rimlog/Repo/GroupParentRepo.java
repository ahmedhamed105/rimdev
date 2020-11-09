package com.rimdev.rimlog.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimlog.entities.GroupParent;
import com.rimdev.rimlog.entities.GroupWeb;

public interface GroupParentRepo extends CrudRepository<GroupParent, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.group_parent where Group_priviledge_ID =?1 " , nativeQuery = true)
	Iterable<GroupParent> getbygroup(int groupid);
	
	
}
