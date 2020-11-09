package com.rimdev.rimcart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimcart.Repo.InputTypeRepo;

@Service
public class InputTypeServ {
	
	@Autowired
	InputTypeRepo inputTypeRepo;

}
