package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.AreaRepo;

@Service
public class AreaServ {
	
	@Autowired 
	private AreaRepo areaRepo;

}
