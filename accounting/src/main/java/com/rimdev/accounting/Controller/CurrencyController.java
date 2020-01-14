package com.rimdev.accounting.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Repo.CurrencyRepo;
import com.rimdev.accounting.Services.AccountServ;
import com.rimdev.accounting.Services.CurrencyServ;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Currency") // 
public class CurrencyController {
	
	@Autowired
	CurrencyServ currencyServ;
	
	
	

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Currency>> getAllUsers(){
		return new ResponseEntity<List<Currency>>(currencyServ.getall(), HttpStatus.OK);
	  }
	
	
	  public  ResponseEntity<List<Currency>> getAllUserserror(int errorcode){
		  if(errorcode == 0) {
				return new ResponseEntity<List<Currency>>(currencyServ.getall(), HttpStatus.BAD_REQUEST);

		  }else if(errorcode == 1) {
			  
				return new ResponseEntity<List<Currency>>(currencyServ.getall(), HttpStatus.CONFLICT);

		  }else {
			  
				return new ResponseEntity<List<Currency>>(currencyServ.getall(), HttpStatus.BAD_REQUEST);

		  }
		  }
	
	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<List<Currency>> saveorupdate(@RequestBody Currency input) {
	    // This returns a JSON or XML with the users
		
		
		
		Currency ouput = null;
		
		if(input.getId() == null || input.getId() == 0) {
			System.out.println("insert");
	
			for(Currency cd:getAllUsers().getBody()) {
				if(cd.getCurrencyISO().equals(input.getCurrencyISO())) {
					return getAllUserserror(1);	 					
				}
				
			}
			
			
			try {
				 ouput= currencyServ.Save(input);
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setError(ouput.getError());
					 return getAllUserserror(0);	 
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setError(ouput.getError());
					 return getAllUserserror(0);	 
				 }
			}
			
			return getAllUsers();

		}else {
			System.out.println("update "+input.getId());
			
			try {
				 ouput= currencyServ.update(input, input.getId());
				 if(ouput == null || ouput.getId() == -1) {
					
					// ouput.setError(ouput.getError());
					 return getAllUserserror(0);	
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null|| ouput.getId() == -1) {
					// ouput.setError(ouput.getError());
					 return getAllUserserror(0);		 
				 }
			}
			
			return getAllUsers();
			
		}
		  }

}
