package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.AdressRepo;



@Service
public class AdressServ {
	
	@Autowired 
	private AdressRepo adressRepo;

}
