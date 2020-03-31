package com.rimdev.user.Controller;

import java.util.ArrayList;
import java.util.List;

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

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.TelephonesServ;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.ouputobject.select_object;


@Controller // This means that this class is a Controller
@RequestMapping(path="/Tele") // 
public class UserTeleController {
	
	@Autowired
	UserServ userServ;
	
	@Autowired
	TelephonesServ telephonesServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	
	  @RequestMapping(value = "/primary/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<select_object>> getprimary( @RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode){
	
		  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

		  List<select_object> sel =new ArrayList<select_object>();
		  select_object a =new select_object();
		  a.setKey("YES");
		  a.setValue("1");
		  sel.add(a);
		  select_object b =new select_object();
		  b.setKey("No");
		  b.setValue("0");
		  sel.add(b);
		return new ResponseEntity<List<select_object>>(sel, HttpStatus.OK);
	  }
	
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Telephones>> getAllUsers( @RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode){
		  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

		  
		  //exception handled
		return new ResponseEntity<List<Telephones>>(telephonesServ.getall(langcode), HttpStatus.OK);
	  }
	  

	  
	  @RequestMapping(value = "/user/{langcode}/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Telephones>> getUsersbyuser( @RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@PathVariable("id") int userid){ 
		  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

		  //exception handled
	    return new ResponseEntity<List<Telephones>>(telephonesServ.getbyuser(userid,langcode), HttpStatus.OK);

		
	  }
	  
	  
	  public  ResponseEntity<List<Telephones>> getUsersbyuser(String langcode,int userid){ 
		  //exception handled
	    return new ResponseEntity<List<Telephones>>(telephonesServ.getbyuser(userid,langcode), HttpStatus.OK);		
	  }
	  

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Telephones>> saveorupdate( @RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody Telephones teles) {
  // This returns a JSON or XML with the users

	  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	if(teles.getId() !=null) {
				
			Telephones found= telephonesServ.getbyid(teles.getId(),langcode);
			
			if(found != null) {
              BeanUtils.copyProperties(teles, found, ObjectUtils.getNullPropertyNames(teles));
              if(!found.getPhoneNo().equals(teles.getPhoneNo())) {
            	  telephonesServ.check_tele(teles.getPhoneNo(),langcode);
              }
  		       telephonesServ.update(found,langcode);      
			}
	}else {
		
			 telephonesServ.check_tele(teles.getPhoneNo(),langcode);
		      telephonesServ.save(teles,langcode);

	}
		
	
	  return getUsersbyuser(langcode,teles.getUserID().getId());

}






@RequestMapping(value = "/delete/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Telephones>> delete( @RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody Telephones teles) {
  // This returns a JSON or XML with the users
	  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	
	if(teles.getId() !=null  && teles.getUserID() != null && teles.getUserID().getId() != null) {
		
		
			Telephones found= telephonesServ.getbyid(teles.getId(),langcode);	
			if(found != null) {
				
				telephonesServ.delete(teles,langcode);
			}
			
		

	}else {
		
		throw new NoDataException("ID not Found or user id not found");

	}
		
	  return getUsersbyuser(langcode,teles.getUserID().getId());

}



}
