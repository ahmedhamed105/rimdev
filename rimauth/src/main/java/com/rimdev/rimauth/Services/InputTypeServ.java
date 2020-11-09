package com.rimdev.rimauth.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimauth.Repo.InputTypeRepo;

@Service
public class InputTypeServ {
	
	@Autowired
	InputTypeRepo inputTypeRepo;

}
