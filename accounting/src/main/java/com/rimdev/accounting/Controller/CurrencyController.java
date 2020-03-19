package com.rimdev.accounting.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Repo.CurrencyRepo;
import com.rimdev.accounting.Services.AccountServ;
import com.rimdev.accounting.Services.CurrencyServ;
import com.rimdev.accounting.Utils.ObjectUtils;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Currency") // 
public class CurrencyController {
	
	@Autowired
	CurrencyServ currencyServ;
	
	
	

	@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Currency>> getAllUsers(@PathVariable("langcode") String langcode){
		return new ResponseEntity<List<Currency>>(currencyServ.getall(langcode), HttpStatus.OK);
	  }
	
	
	
	
	@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<List<Currency>> saveorupdate(@PathVariable("langcode") String langcode,@RequestBody Currency input) {
	    // This returns a JSON or XML with the users
		
		Currency user=null;
		try {
			 user= currencyServ.getcurrency(input.getId(),langcode);
		//	 System.out.println("enter 2");

			if(user == null ) {
			//	System.out.println("enter 3");
				user=currencyServ.Save(input,langcode);
			
			}else {
		//		System.out.println("enter 4");
		    BeanUtils.copyProperties(input, user, ObjectUtils.getNullPropertyNames(input));
		 //   System.out.println(user.getFirstName());
		    user=currencyServ.update(user,langcode);
				
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("enter 5");
			user= currencyServ.Save(input,langcode);

		}

			return getAllUsers(langcode);
			
		  }

}
