package com.rimdev.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Services.LanguagesServ;
import com.rimdev.user.entities.Languages;


@Controller // This means that this class is a Controller
@RequestMapping(path="/langs") // 
public class LanguagesController {
	
	@Autowired
	LanguagesServ languagesServ;
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Languages>> getUsersbyuser(@PathVariable("langcode") String langcode){ 
		  return new ResponseEntity<List<Languages>>(languagesServ.getalllang(langcode), HttpStatus.OK);
	  }

}
