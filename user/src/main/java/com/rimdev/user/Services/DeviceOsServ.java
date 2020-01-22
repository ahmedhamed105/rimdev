package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.AreaRepo;
import com.rimdev.user.Repo.DeviceOsRepo;

@Service
public class DeviceOsServ {
	
	@Autowired 
	private DeviceOsRepo deviceOsRepo;

}
