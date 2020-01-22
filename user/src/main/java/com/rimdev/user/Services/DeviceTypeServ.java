package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rimdev.user.Repo.DeviceTypeRepo;


@Service
public class DeviceTypeServ {
	
	@Autowired 
	private DeviceTypeRepo deviceTypeRepo;

}
