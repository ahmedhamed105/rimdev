package com.rimdev.accounting.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.outputobject.Status;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Status") // 
public class StatusController {
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Status>> getAllstatus() {
	    // This returns a JSON or XML with the users
		
		List<Status> a=new ArrayList<Status>();
		a.add(new Status(1,"Active"));
		a.add(new Status(2,"Closed"));
		
		return new ResponseEntity<List<Status>>(a, HttpStatus.OK);
	  }
	

}
