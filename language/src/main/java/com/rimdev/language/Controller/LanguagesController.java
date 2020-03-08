package com.rimdev.language.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.language.Entities.Languages;
import com.rimdev.language.Services.LanguagesServ;

@Controller // This means that this class is a Controller
@RequestMapping(path="/langs") // 
public class LanguagesController {
	
	@Autowired
	LanguagesServ languagesServ;
	
	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Languages>> getUsersbyuser(){ 
		  return new ResponseEntity<List<Languages>>(languagesServ.getalllang(), HttpStatus.OK);
	  }

}
