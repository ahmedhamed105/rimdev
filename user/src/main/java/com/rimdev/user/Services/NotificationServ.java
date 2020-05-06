package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.NotificationRepo;
import com.rimdev.user.entities.Notification;

@Service
public class NotificationServ {
	
	@Autowired
	NotificationRepo notificationRepo;
	
	@Autowired
	ConfigurationServ configurationServ;
	
	
	
	public List<Notification> getbygroup(int groupid){
		try {
			int count=configurationServ.getbykey("notification_count").getConfignum();
			System.out.println(count+" "+groupid);
			return (List<Notification>) notificationRepo.getbygroup(groupid,count);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ArrayList<Notification>();
		}
		
		
	}

}
