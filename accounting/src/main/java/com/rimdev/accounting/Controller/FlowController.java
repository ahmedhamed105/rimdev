package com.rimdev.accounting.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Exception.PopupException;
import com.rimdev.accounting.Services.ExternalServ;
import com.rimdev.accounting.Services.FlowTypeServ;
import com.rimdev.accounting.Services.TextConvertionServ;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Flow") // 
public class FlowController {
	
	
	@Autowired
	FlowTypeServ flowTypeServ;

	@Autowired
	ExternalServ externalServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	


	
	@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<FlowType>> getAllUsers(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode) {
	    // This returns a JSON or XML with the users
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "Currency in processing", "", 34, 0);
		if(result) {
			
			return new ResponseEntity<List<FlowType>>(flowTypeServ.getall(langcode), HttpStatus.OK);

		}else {
			
			throw new PopupException(textConvertionServ.search("no_data", langcode));

		}

	
	}
	
	
	@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<FlowType> saveorupdate(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody FlowType input) {
	    // This returns a JSON or XML with the users
		FlowType user=null;
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "Currency in processing", "", 34, 0);

		if(result) {
		
		try {
			 user= flowTypeServ.getflow(input.getId(),langcode);
			if(user == null ) {
				user=flowTypeServ.Save(input,langcode);
				 externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type inserted", "", 35, 0);

			
			}else {
				input.setId(user.getId());
		    user=flowTypeServ.update(input,langcode);
			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type updated", "", 35, 0);

				
			}	

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("enter 5");
			user= flowTypeServ.Save(input,langcode);

		}

		return new ResponseEntity<FlowType>(user, HttpStatus.OK);
			
			
		}else {
			
			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type Error in processing", "while auth and log in first", 35, 1);
			throw new PopupException(textConvertionServ.search("auth_error", langcode));
			
		}
			
		
		  }

}
