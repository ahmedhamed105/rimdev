package com.rimdev.user.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.DeviceStatusRepo;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DeviceOs;
import com.rimdev.user.entities.DeviceStatus;
import com.rimdev.user.entities.DeviceType;

@Service
public class DeviceStatusServ {
	
	@Autowired 
	private DeviceStatusRepo deviceStatusRepo;
	
	
	public DeviceStatus getbyid(int id) {
		
		
		try {
			Optional<DeviceStatus> flowid =deviceStatusRepo.findById(id);
			 
			 if (flowid.isPresent()){
				 DeviceStatus  ouput = flowid.get();
			
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
