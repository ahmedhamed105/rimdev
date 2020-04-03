package com.rimdev.user.Controller;

import java.util.List;

import javax.validation.constraints.NotNull;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserFile;
import com.rimdev.user.ouputobject.threevalues;

@Controller // This means that this class is a Controller
@RequestMapping(path="/User") // 
public class UserController {
	

	@Autowired
	UserServ userServ;
	
	
	@Autowired
	DevicePageServ devicePageServ;
	

	
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<User>> getAllUsers(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode){

		  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);
  
		  return new ResponseEntity<List<User>>(userServ.getall(langcode), HttpStatus.OK);
	  }
	  
	  
	  
	  public  ResponseEntity<List<User>> getAllUsers(String langcode){
		  return new ResponseEntity<List<User>>(userServ.getall(langcode), HttpStatus.OK);
	  }
	  
	  @RequestMapping(value = "/get/{langcode}/{id}", method = RequestMethod.GET)
	  public ResponseEntity<User> getuser(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@PathVariable @NotNull int id){
	
		  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);
  
		  User a = new User();
		  try {
			 a = userServ.getuser(id,langcode);
			 if(a == null) {
			  return new ResponseEntity<User>(a, HttpStatus.NOT_FOUND); 	 
			 }
			return new ResponseEntity<User>(a, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<User>(a, HttpStatus.NOT_FOUND);
		}
	  }
	  
	 

@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<User>> saveorupdate(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody User input) {
	  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	
	// This returns a JSON or XML with the users
//System.out.println("enter 1");
//System.out.println(input.getFirstName());
	User user=null;
	try {
		 user= userServ.getuserwithout(input.getId(),langcode);
	//	 System.out.println("enter 2");

		if(user == null ) {
		//	System.out.println("enter 3");
			user=userServ.Save(input,langcode);
		
		}else {
	//		System.out.println("enter 4");
	    BeanUtils.copyProperties(input, user, ObjectUtils.getNullPropertyNames(input));
	 //   System.out.println(user.getFirstName());
	    user=userServ.update(user,langcode);
			
		}
		

	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("enter 5");
		user= userServ.Save(input,langcode);

	}


	 return getAllUsers(langcode);

	
}



@RequestMapping(value = "/file/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<FilesUpload> savefile(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody threevalues input) {
 
	  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	// This returns a JSON or XML with the users
//System.out.println("enter 1");
//System.out.println(input.getFirstName());
	System.out.println(input.getValue1() +" "+input.getValue2() +" "+input.getValue3());
	
	FilesUpload out= userServ.savefile(input, langcode);
	
	 return new ResponseEntity<FilesUpload>(out, HttpStatus.OK);

	
}



@RequestMapping(value = "/filebyuser/{langcode}/{id}", method = RequestMethod.GET)
public @ResponseBody ResponseEntity<List<UserFile>> savefile(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@PathVariable("id") String userid) {
 
	  DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	// This returns a JSON or XML with the users
//System.out.println("enter 1");
//System.out.println(input.getFirstName());
	System.out.println(userid);
	
	List<UserFile> out= userServ.getfile(userid, langcode);
	
	 return new ResponseEntity<List<UserFile>>(out, HttpStatus.OK);

	
}


@RequestMapping(value = "/deletefile/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<FilesUpload> deletefile(@RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestParam("fileid") String fileid,@RequestParam("object") String object,@RequestParam("component") String componentid) {
	
	DevicePage dg= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	
	// This returns a JSON or XML with the users
//System.out.println("enter 1");
//System.out.println(input.getFirstName());
	System.out.println(fileid+" "+ object+" "+componentid);
	
	FilesUpload out= userServ.deletefile(fileid, object, componentid, langcode);
	
	 return new ResponseEntity<FilesUpload>(out, HttpStatus.OK);

	
}



	  
	  


}
