package com.rimdev.rimfile.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimfile.Repo.InputTypeRepo;

@Service
public class InputTypeServ {
	
	@Autowired
	InputTypeRepo inputTypeRepo;

}
