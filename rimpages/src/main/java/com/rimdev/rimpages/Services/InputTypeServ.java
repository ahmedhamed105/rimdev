package com.rimdev.rimpages.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimpages.Repo.InputTypeRepo;

@Service
public class InputTypeServ {
	
	@Autowired
	InputTypeRepo inputTypeRepo;

}
