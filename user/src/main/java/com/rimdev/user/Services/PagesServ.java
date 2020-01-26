package com.rimdev.user.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.rimdev.user.Repo.DevicePageRepo;
import com.rimdev.user.Repo.PagesRepo;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Pages;

@Service
public class PagesServ {
	
	@Autowired 
	private PagesRepo pagesRepo;
	
	@Autowired
	DevicePageRepo devicePageRepo;
	
	
public List<Pages> getall() {
		
		return (List<Pages>) pagesRepo.findAll();
		
	}

public void savedevpag(Device dev,Pages pa) {
	Date visittime = new Date();
	DevicePage a=new DevicePage();
	a.setDeviceId(dev);
	a.setPagesID(pa);
	a.setVisittime(visittime);
	devicePageRepo.save(a);
	
}


public Pages getbyid(int id) {
	
	
	try {
		Optional<Pages> flowid =pagesRepo.findbyid(id);
		 
		 if (flowid.isPresent()){
			 Pages  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return null;
			}
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	
	

	
	
	
}

}
