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
import com.rimdev.user.Services.AreaServ;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.EmailServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.ouputobject.response_all;
import com.rimdev.user.ouputobject.select_object;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Email") // 
public class EmailController {
	
	@Autowired
	EmailServ emailServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	
	  @RequestMapping(value = "/primary/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<select_object>> getprimary(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode){
		
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
	  public  ResponseEntity<List<Email>> getAllUsers(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode){
	
		  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);
	  
		  return new ResponseEntity<List<Email>>(emailServ.getall(langcode), HttpStatus.OK);
	  }
	  
	  
	  @RequestMapping(value = "/user/{langcode}/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Email>> getUsersbyuser(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@PathVariable("id") int userid){ 
		  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

		  
		  return new ResponseEntity<List<Email>>(emailServ.getbyuserlogin(userid,langcode), HttpStatus.OK);
	  }
	  
	  
	  public  ResponseEntity<List<Email>> getUsersbyuser( String langcode, int userid){ 

		  return new ResponseEntity<List<Email>>(emailServ.getbyuserlogin(userid,langcode), HttpStatus.OK);
	  }

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Email>> saveorupdate(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody Email emails) {
  // This returns a JSON or XML with the users
	
	  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	
	if(emails.getId() !=null) {
		
	
			Email found= emailServ.getbyid(emails.getId(),langcode);
			
			if(found != null) {
              BeanUtils.copyProperties(emails, found, ObjectUtils.getNullPropertyNames(emails));
            
              if(!found.getEmailuser().equals(emails.getEmailuser())) {
            	  emailServ.check_email(emails.getEmailuser(),langcode);
              }
              
  			
              emailServ.update(found,langcode);

			}
	}else {
	
			 emailServ.check_email(emails.getEmailuser(),langcode);
		     emailServ.save(emails,langcode);
	}
		
	
	
	
		return getUsersbyuser(langcode,emails.getUserloginID().getId());
}






@RequestMapping(value = "/delete/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Email>> delete(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody Email emails) {
  // This returns a JSON or XML with the users
	
	  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	if(emails.getId() !=null  && emails.getUserloginID() != null && emails.getUserloginID().getId() != null) {
		
		
		Email found= emailServ.getbyid(emails.getId(),langcode);	
		if(found != null) {
			
			emailServ.delete(emails,langcode);
		}
		
	

}else {
	
	throw new NullPointerException("ID not Found or user id not found");

}
	
	
	return getUsersbyuser(langcode,emails.getUserloginID().getId());
}


}
