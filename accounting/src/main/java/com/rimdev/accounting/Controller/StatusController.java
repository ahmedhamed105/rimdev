package com.rimdev.accounting.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.accounting.Enttities.AllStatus;
import com.rimdev.accounting.Exception.PopupException;
import com.rimdev.accounting.Services.AllStatusServ;
import com.rimdev.accounting.Services.ExternalServ;
import com.rimdev.accounting.Services.TextConvertionServ;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Status") // 
public class StatusController {
	
	@Autowired
	AllStatusServ allStatusServ;
	
	
	@Autowired
	ExternalServ externalServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<AllStatus>> getAllstatus(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode) {
	    // This returns a JSON or XML with the users
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "Currency in processing", "", 34, 0);
		if(result) {
		return new ResponseEntity<List<AllStatus>>(allStatusServ.getall(langcode), HttpStatus.OK);
				
		}else {
			
			throw new PopupException(textConvertionServ.search("no_data", langcode));

		}
	  }
	

}
