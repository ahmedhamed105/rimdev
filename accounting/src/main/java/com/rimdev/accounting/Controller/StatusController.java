package com.rimdev.accounting.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.accounting.Enttities.AllStatus;
import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Services.AllStatusServ;
import com.rimdev.accounting.outputobject.Status;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Status") // 
public class StatusController {
	
	@Autowired
	AllStatusServ allStatusServ;
	
	@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<AllStatus>> getAllstatus(@PathVariable("langcode") String langcode) {
	    // This returns a JSON or XML with the users
		return new ResponseEntity<List<AllStatus>>(allStatusServ.getall(langcode), HttpStatus.OK);

	  }
	

}
