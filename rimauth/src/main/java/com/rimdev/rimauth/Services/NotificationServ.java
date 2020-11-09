package com.rimdev.rimauth.Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimauth.Repo.NotificationRepo;
import com.rimdev.rimauth.entities.Application;
import com.rimdev.rimauth.entities.GroupPriviledge;
import com.rimdev.rimauth.entities.Notification;
import com.rimdev.rimauth.entities.UserLogin;

@Service
public class NotificationServ {
	
	@Autowired
	NotificationRepo notificationRepo;
	
	@Autowired
	ConfigurationServ configurationServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
	
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
	
	


public void save(String notiftext,Application applicationID,GroupPriviledge grouppriviledgeID,UserLogin userloginID,String langcode) {

	 Calendar cal = Calendar.getInstance(); 
	Notification notif=new Notification();
	notif.setNotifDate(cal.getTime());
	notif.setApplicationID(applicationID);
	notif.setGrouppriviledgeID(grouppriviledgeID);
	notif.setUserloginID(userloginID);
	notif.setNotiftext(notiftext);
		try {
			notificationRepo.save(notif);	
		} catch (TransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (ScriptException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }catch (NonTransientDataAccessException  se) {
			throw new NullPointerException(textConvertionServ.search("E104", langcode));
	    }
}

}
