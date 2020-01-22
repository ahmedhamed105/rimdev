package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.TelephonesRepo;

@Service
public class TelephonesServ {
	
	@Autowired 
	private TelephonesRepo telephonesRepo;

}
