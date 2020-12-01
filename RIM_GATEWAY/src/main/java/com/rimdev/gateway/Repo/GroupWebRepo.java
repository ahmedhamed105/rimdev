package com.rimdev.gateway.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rimdev.gateway.entities.GroupWeb;

public interface GroupWebRepo  extends CrudRepository<GroupWeb, Integer>{
	
	
	@Query(value ="SELECT * FROM rim_user.group_web where Group_priviledge_ID =?1 " , nativeQuery = true)
	Iterable<GroupWeb> getbygroup(int groupid);

}