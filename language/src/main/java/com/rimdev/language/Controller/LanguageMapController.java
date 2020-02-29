package com.rimdev.language.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.language.Entities.TextConvertion;
import com.rimdev.language.Object.inputsearch;
import com.rimdev.language.Services.LanguageMapServ;

@Controller // This means that this class is a Controller
@RequestMapping(path="/langmap") // 
public class LanguageMapController {
	
	@Autowired
	LanguageMapServ languageMapServ;
	
	
	
	
}
