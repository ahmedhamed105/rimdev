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

import com.rimdev.accounting.Enttities.AllStatus;
import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Exception.PopupException;
import com.rimdev.accounting.Services.AllStatusServ;
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
	
	@Autowired
	AllStatusServ allStatusServ;
	


	
	@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<FlowType>> getAll(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode) {
	    // This returns a JSON or XML with the users
		try {
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type in processing", "", 35, 0);
		if(result) {
			
			return new ResponseEntity<List<FlowType>>(flowTypeServ.getall(langcode), HttpStatus.OK);

		}else {
			
			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
					"flow type Error in processing", "while auth and log in first", 35, 1);
			throw new PopupException(textConvertionServ.search("auth_error", langcode));
		}
	} catch (Exception e) {

		throw new PopupException(textConvertionServ.search("no_data", langcode));
	}
	
	}
	
	
	@RequestMapping(value = "/save/{langcode}", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<List<FlowType>> save(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody FlowType input) {
	    // This returns a JSON or XML with the users
		FlowType user=null;
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type insert in processing", "", 35, 0);

		if(result) {
		
		try {

			if (input.getId() == null || input.getAllstatusID().getId() == null) {
				AllStatus status = allStatusServ.getbyid(input.getAllstatusID().getId(), langcode);
				input.setAllstatusID(status);
				user=flowTypeServ.Save(input,langcode);
				 externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type inserted", "", 35, 0);

			
			}else {
				throw new PopupException("error while insertion");	
			}	

		} catch (Exception e) {
			throw new PopupException("error while insertion");

		}

		return getAll(request, Devicecode, username, usertokean, pagenum, langcode);
			
			
		}else {
			
			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
					"flow type insert Error in processing", "while auth and log in first", 35, 1);
			throw new PopupException(textConvertionServ.search("auth_error", langcode));
		}
			
		
		  }
	
	
	@RequestMapping(value = "/update/{langcode}", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<List<FlowType>> saveorupdate(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody FlowType input) {
	    // This returns a JSON or XML with the users
		FlowType user=null;
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type update in processing", "", 35, 0);

		if(result) {
			
			if (input.getId() == null ||  input.getAllstatusID().getId() == null) {

				throw new PopupException("error while updating");

			} else {
				
		try {
			 user= flowTypeServ.getflow(input.getId(),langcode);

			if(user == null ) {
				throw new PopupException("error while updating");
			}else {
	

				AllStatus status = allStatusServ.getbyid(input.getAllstatusID().getId(), langcode);
				input.setAllstatusID(status);
		    user=flowTypeServ.update(user, input,langcode);
			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "flow type updated", "", 35, 0);

				
			}	

		} catch (Exception e) {
			// TODO: handle exception
			throw new PopupException("error while updating");
		}

			}
			
			return getAll(request, Devicecode, username, usertokean, pagenum, langcode);

			
		}else {
			
			externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode,
					"flow type update Error in processing", "while auth and log in first", 35, 1);
			throw new PopupException(textConvertionServ.search("auth_error", langcode));
		}
			
		
		  }

}
