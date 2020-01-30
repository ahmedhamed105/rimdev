package com.rimdev.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Services.EmailServ;
import com.rimdev.user.Services.PasswordHistoryServ;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.PasswordHistory;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Password") // 
public class passHistoryController {
	
	
	@Autowired
	PasswordHistoryServ passwordHistoryServ;

	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<PasswordHistory>> getAllUsers(){
		return new ResponseEntity<List<PasswordHistory>>(passwordHistoryServ.getall(), HttpStatus.OK);
	  }
	  

}
