package com.rimdev.rimlang.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimlang.Repo.InputTypeRepo;

@Service
public class InputTypeServ {
	
	@Autowired
	InputTypeRepo inputTypeRepo;

}
