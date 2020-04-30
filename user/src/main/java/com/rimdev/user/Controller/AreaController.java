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

import com.rimdev.user.Services.AreaServ;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.DevicePage;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Area") // 
public class AreaController {
	
	@Autowired
	AreaServ areaServ;
	
	@Autowired
	DevicePageServ devicePageServ;

	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Area>> getAllUsers(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
		
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		  
		  return new ResponseEntity<List<Area>>(areaServ.getall(), HttpStatus.OK);
	  }
	  
	  

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Area>> saveorupdate(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody List<Area> Areas) {
  // This returns a JSON or XML with the users
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

	for (Area input : Areas) {
		
	
	
	if(input.getId()==null) {
		
		try {
			
			Area found= areaServ.getbyid(input.getId());
			
			if(found == null) {
				areaServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				areaServ.update(found);
				
			}
				
			} catch (Exception e) {
				// TODO: handle exception
				areaServ.save(input);
			}
			

	}else {
		try {
			
		Area found= areaServ.check_area(input.getAreaname());
		
		if(found == null) {
			areaServ.save(input);
		}else {
			  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

			areaServ.update(found);
			
		}
			
		} catch (Exception e) {
			// TODO: handle exception
			areaServ.save(input);
		}
		

	}
		
	}
	
	
	return new ResponseEntity<List<Area>>(areaServ.getall(), HttpStatus.OK);

}


}
