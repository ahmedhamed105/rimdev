package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.UserDeviceRepo;

@Service
public class UserDeviceServ {
	
	@Autowired 
	private UserDeviceRepo userDeviceRepo;

}
