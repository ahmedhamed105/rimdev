package com.rimdev.accounting.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Services.FlowTypeServ;
import com.rimdev.accounting.Services.TextConvertionServ;
import com.rimdev.accounting.Utils.ObjectUtils;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Flow") // 
public class FlowController {
	
	
	@Autowired
	FlowTypeServ flowTypeServ;

	@Autowired
	TextConvertionServ textConvertionServ;
	


	
	@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<FlowType>> getAllUsers(@PathVariable("langcode") String langcode) {
	    // This returns a JSON or XML with the users

		return new ResponseEntity<List<FlowType>>(flowTypeServ.getall(langcode), HttpStatus.OK);

	
	}
	
	
	@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<List<FlowType>> saveorupdate(@PathVariable("langcode") String langcode,@RequestBody FlowType input) {
	    // This returns a JSON or XML with the users
		FlowType user=null;
		try {
			 user= flowTypeServ.getflow(input.getId(),langcode);
		//	 System.out.println("enter 2");

			if(user == null ) {
			//	System.out.println("enter 3");
				user=flowTypeServ.Save(input,langcode);
			
			}else {
		//		System.out.println("enter 4");
		    BeanUtils.copyProperties(input, user, ObjectUtils.getNullPropertyNames(input));
		 //   System.out.println(user.getFirstName());
		    user=flowTypeServ.update(user,langcode);
				
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("enter 5");
			user= flowTypeServ.Save(input,langcode);

		}

			return getAllUsers(langcode);
			
		
		  }

}
