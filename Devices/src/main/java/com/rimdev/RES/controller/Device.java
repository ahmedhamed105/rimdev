package com.rimdev.RES.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rimdev.RES.Input.InDevice;
import com.rimdev.RES.Input.Regiter;
import com.rimdev.RES.Service.devicetableServ;
import com.rimdev.RES.entites.Device_table;
import com.rimdev.RES.output.DevToken;

@RestController
@RequestMapping(value = "/RIM/Devices")
public class Device {
	

	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ResponseEntity<DevToken> register_user(@RequestBody InDevice input) {
		
		DevToken output = new DevToken();
		
		output.setDevToken("Error");
		
		 if (input == null) {
			 
			 // null input
				return new ResponseEntity<DevToken>(output, HttpStatus.NOT_FOUND);
		    }
		 
		
		if(input.getApptype() == 1) {
			
			// Desktop condition
			if(input.getIP().equals("") || input.getIP() == null || input.getMAC().equals("") || input.getMAC() == null  || input.getOS().equals("") || input.getOS() == null ) {
				 // ip , MAC , OS is mandatory 
				return new ResponseEntity<DevToken>(output, HttpStatus.NOT_FOUND);
		  	
			}else {
				
			

				return new ResponseEntity<DevToken>(output, HttpStatus.OK);	
				
			}
			
			
			


		}
		return null;

		  
	}
	


}
