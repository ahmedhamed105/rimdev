package com.rimdev.accounting.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Services.AccountServ;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Account") // 
public class AccountController {
	
	@Autowired
	AccountServ accountServ;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Account>> getAllUsers() {
	    // This returns a JSON or XML with the users
		
		return new ResponseEntity<List<Account>>(accountServ.getall(), HttpStatus.OK);
	  }
	
	

	
	
	

}
