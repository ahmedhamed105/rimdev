package com.rimdev.accounting.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Services.AccountProcessServ;
import com.rimdev.accounting.Services.AccountServ;
import com.rimdev.accounting.inputobject.Acct_obj;
import com.rimdev.accounting.inputobject.authobject;
import com.rimdev.accounting.inputobject.authouput;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Account") // 
public class AccountController {
	
	@Autowired
	AccountServ accountServ;
	
	@Autowired
	AccountProcessServ accountProcessServ;
	

	
	@RequestMapping(value = "/create/{langcode}", method = RequestMethod.POST)
	  public  @ResponseBody ResponseEntity<Acct_obj> create_account(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Acct_obj acct) {
	    // This returns a JSON or XML with the users
		  try {
		  RestTemplate restTemplate = new RestTemplate();
		  ObjectMapper mapper = new ObjectMapper();
		  authobject auth=new authobject();
		  auth.setRequestip(request.getRemoteAddr());
		  auth.setRequestURL(request.getRequestURI().toString());
		  auth.setDevicecode(Devicecode);
		  auth.setLangcode(langcode);
		  auth.setPagenum(pagenum);
		  auth.setUsername(username);
		  auth.setUsertokean(usertokean);
		  auth.setParamter(new ArrayList<>());
		  auth.setValues(new ArrayList<>());  
		  auth.setLogtext("Account in processing");
		  auth.setInfo(true);
		  auth.setLogtype(33);
		  auth.setLogException("");
		  final HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		 // set `accept` header
		 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		     System.out.println(mapper.writeValueAsString(auth));

		    //Create a new HttpEntity
		    
				HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(auth),headers);
			
			
		  
		  ResponseEntity<authouput> okl = restTemplate.postForEntity("http://localhost:8081/Auth/log/"+langcode,entity, authouput.class);

		
		if(okl.getStatusCodeValue() == 200) {
			
			return new ResponseEntity<Acct_obj>(accountProcessServ.create_acct(acct), HttpStatus.OK);
	
		}else {
		
			return new ResponseEntity<Acct_obj>(new Acct_obj(), HttpStatus.NOT_ACCEPTABLE);

			
		}
		
		     } catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new ResponseEntity<Acct_obj>(new Acct_obj(), HttpStatus.NOT_ACCEPTABLE);

				}
				
	  }
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Account>> getAllUsers() {
	    // This returns a JSON or XML with the users
		
		return new ResponseEntity<List<Account>>(accountServ.getall(), HttpStatus.OK);
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
