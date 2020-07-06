package com.rimdev.user.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
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

import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.PagesServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Pages;
@Controller // This means that this class is a Controller
@RequestMapping(path="/Page") // 
public class Pagescontroller {
	
	@Autowired
	PagesServ pagesServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	
	 @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Pages>> getAll(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		 return new ResponseEntity<List<Pages>>(pagesServ.getall(langcode), HttpStatus.OK);
	  }
	 

	  public  ResponseEntity<List<Pages>> getAll(String langcode){
		 return new ResponseEntity<List<Pages>>(pagesServ.getall(langcode), HttpStatus.OK);
	  }  

@RequestMapping(value = "/save/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Pages>> save(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Pages teles) {
  // This returns a JSON or XML with the users
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

try {
	if(teles.getId() ==null) {
		
		
			 pagesServ.check_page(teles.getPagename(),langcode);
			 
		      pagesServ.save(teles,langcode);

	} else {
		throw new PopupException("error while insertion");
	}
		
} catch (Exception e) {
	// TODO: handle exception
	throw new PopupException("error while insertion");
}

return getAll(request, Devicecode, username, usertokean, pagenum, langcode);

}


@RequestMapping(value = "/update/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Pages>> update(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Pages teles) {
  // This returns a JSON or XML with the users
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);


	if (teles.getId() == null) {

		throw new PopupException("error while updating");

	} else {
		try {
				
		Pages found= pagesServ.getbyid(teles.getId(),langcode);
		if (found == null) {
			// System.out.println("enter 3");
			throw new PopupException("error while updating");

		} else {
			
            	  pagesServ.check_page(teles.getPagename(),langcode);
  		       pagesServ.update(found,teles,langcode);      
			}
		} catch (Exception e) {
			// TODO: handle exception

			throw new PopupException("error while updating");
		}

	}
		
	
	return getAll(request, Devicecode, username, usertokean, pagenum, langcode);

}









}
