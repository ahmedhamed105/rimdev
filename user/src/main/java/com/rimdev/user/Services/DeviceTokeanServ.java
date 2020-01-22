package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.DeviceTokeanRepo;

@Service
public class DeviceTokeanServ {
	
	@Autowired 
	private DeviceTokeanRepo deviceTokeanRepo;

}
