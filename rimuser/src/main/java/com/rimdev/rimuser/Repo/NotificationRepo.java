package com.rimdev.rimuser.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.rimdev.rimuser.entities.Notification;

public interface NotificationRepo  extends CrudRepository<Notification, Integer> {
	
	@Query(value ="SELECT * FROM rim_user.notification where Group_priviledge_ID =?1  ORDER BY Notif_Date DESC LIMIT ?2" , nativeQuery = true)
	Iterable<Notification> getbygroup(int groupid,int count);
	
}
