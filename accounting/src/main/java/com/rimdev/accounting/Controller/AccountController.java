package com.rimdev.accounting.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Services.AccountServ;
import com.rimdev.accounting.Services.ErrorCodesServ;

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
	
	
	@RequestMapping(value = "/allstatus", method = RequestMethod.GET)
	  public  ResponseEntity<List<Account>> getAllby(@RequestBody Account input) {
	    // This returns a JSON or XML with the users
		
		if(input.getAcctstatus() != null) {
			
			return new ResponseEntity<List<Account>>(accountServ.getallstatus(input.getAcctstatus()), HttpStatus.OK);
	
		}else {
		
		return new ResponseEntity<List<Account>>(accountServ.getall(), HttpStatus.OK);
		}
	  }
	
	
	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<Account> saveorupdate(@RequestBody Account input) {
	    // This returns a JSON or XML with the users
		
		Account ouput = null;
		
		if(input.getId() == null || input.getId() == 0) {
			System.out.println("insert");
			try {
				 ouput= accountServ.Save(input);
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<Account>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<Account>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			}
			
			return new ResponseEntity<Account>(ouput, HttpStatus.OK);

		}else {
			System.out.println("update "+input.getId());
			
			try {
				 ouput= accountServ.update(input, input.getId());
				 if(ouput == null || ouput.getId() == -1) {
					
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<Account>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null|| ouput.getId() == -1) {
					 
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<Account>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			}
			
			return new ResponseEntity<Account>(ouput, HttpStatus.OK);
			
		}
		  }
	
	

	
	
	

}
