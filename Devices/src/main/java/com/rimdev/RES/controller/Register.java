package com.rimdev.RES.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rimdev.RES.Input.Regiter;
import com.rimdev.RES.Service.loginService;

@RestController
@RequestMapping(value = "/application")
public class Register {
	
	
	@Autowired
	loginService loginservice;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<com.rimdev.RES.output.Register> register_user(@RequestBody Regiter input) {
		
		com.rimdev.RES.output.Register output = new com.rimdev.RES.output.Register();
		
		 if (input != null) {
			 output.setConfirm("OK");
			 output.setError(input.getUsername());
		    }
		 
		
		if(! input.getPassword().equals(input.getConfirm_password())) {
			 output.setConfirm("not the same password");
			 output.setError(input.getUsername());

			return new ResponseEntity<com.rimdev.RES.output.Register>(output, HttpStatus.FORBIDDEN);
	
		}
		
		int a = loginservice.checkUsernameAndPassword(input);

		
	 if(a==1){
				 output.setConfirm("NO");
				 output.setError("duplicate password");
			return new ResponseEntity<com.rimdev.RES.output.Register>(output, HttpStatus.CONFLICT);
		}else {
			  // TODO: call persistence layer to update
		    return new ResponseEntity<com.rimdev.RES.output.Register>(output, HttpStatus.CREATED);
		
			
		}
		
		


		  
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Regiter> register_user() {
		
		Regiter output = new Regiter();
		
		output.setUsername("ahmed");
		output.setBrowser("ahmed");
		output.setConfirm_password("ahmed");
		output.setEmail("ahmed");
		output.setIp("ahmed");
		output.setLand_phone("ahmed");
		output.setMobile("ahmed");
		output.setPassword("ahmed");
		output.setPostal_code("ahmed");
		

		    // TODO: call persistence layer to update
		    return new ResponseEntity<Regiter>(output, HttpStatus.OK);
		
	}

}
