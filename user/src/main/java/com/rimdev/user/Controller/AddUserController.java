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

import com.rimdev.user.Services.AdressServ;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.TelephonesServ;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Adress;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Address") // 
public class AddUserController {
	
	
	@Autowired
	UserServ userServ;
	
	@Autowired
	AdressServ adressServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Adress>> getAllUsers(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
		  
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	 
		return new ResponseEntity<List<Adress>>(adressServ.getall(), HttpStatus.OK);
	  }

	  


@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Adress>> saveorupdate(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody List<Adress> emails) {
  // This returns a JSON or XML with the users
	
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

	for (Adress input : emails) {
		
	
	
	if(input.getId() !=null) {
		
		try {
			Adress found= adressServ.getbyid(input.getId());
			if(found == null) {
				
				adressServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				  adressServ.update(found);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			adressServ.save(input);

		}

	}else {
		try {
			Adress found= adressServ.check_address(input.getAdname());
			if(found == null) {
				
				adressServ.save(input);
			}else {
				  BeanUtils.copyProperties(input, found, ObjectUtils.getNullPropertyNames(input));

				  adressServ.update(found);

			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			adressServ.save(input);

		}



	}
		
	}
	
	
	return new ResponseEntity<List<Adress>>(adressServ.getall(), HttpStatus.OK);

}


}
