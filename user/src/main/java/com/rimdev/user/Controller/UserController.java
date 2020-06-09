package com.rimdev.user.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

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
import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Services.AccountServ;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.NotificationServ;
import com.rimdev.user.Services.TextConvertionServ;
import com.rimdev.user.Services.UserFileServ;
import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.FilesUpload;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserFile;
import com.rimdev.user.entities.UserLogin;
import com.rimdev.user.ouputobject.Userobject;
import com.rimdev.user.ouputobject.threevalues;

@Controller // This means that this class is a Controller
@RequestMapping(path="/User") // 
public class UserController {
	

	@Autowired
	UserServ userServ;
	
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	UserLoginServ  userLoginServ;
	
	@Autowired
	NotificationServ notificationServ;
	
	
	@Autowired
	AccountServ accountServ;
	
	@Autowired
	UserFileServ userFileServ;
	
	
	@Autowired
	TextConvertionServ textConvertionServ;
	

	
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Userobject>> getAllUsers(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
try {
	  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

	  return new ResponseEntity<List<Userobject>>(userServ.getall(langcode), HttpStatus.OK);

} catch (Exception e) {
	// TODO: handle exception
	throw new PopupException(textConvertionServ.search("no_data", langcode));
}
			  }
	  
	  
	  

	  @RequestMapping(value = "/get/{langcode}/{id}", method = RequestMethod.GET)
	  public ResponseEntity<User> getuser(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@PathVariable @NotNull int id){
	
		  List<String> paramter =new ArrayList<String>();
		  List<String> values =new ArrayList<String>();
		  paramter.add("id");
		  values.add(String.valueOf(id));		  
		  DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

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
public @ResponseBody ResponseEntity<UserLogin> saveorupdate(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Userobject input) {
	
	List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

if(input.getUser().getiDnumber() != null && !input.getUser().getiDnumber().equals("")) {
input.getUser().setiDnumber(userLoginServ.dencryp(input.getUser().getiDnumber()));
}

User user;
if(input.getUser().getId() == null) {
	
	 user=userServ.Save(request,dg,input.getUser(),langcode);
	 
}else {
	 user= userServ.getuserbyid(input.getUser().getId(),langcode);
		if(user == null ) {
			user=userServ.Save(request,dg,input.getUser(),langcode);
		
		}else {
	     user=userServ.update(request,dg,user,input.getUser(),langcode);		
		}
		
}


input.getLogin().setUserID(user);


UserLogin info =input.getLogin();

  
 
if(info.getId() !=null) {
	UserLogin found= userLoginServ.getbyid(info.getId(),langcode);
		
		if(found != null) {  
			if(!found.getUsername().equals(info.getUsername())) {
				userLoginServ.check_username(info.getUsername(),langcode);	
			}
          
      	System.out.println(info.toString());
          userLoginServ.update(found,info,langcode);
          notificationServ.save("User "+info.getUsername()+" updated", info.getApplicationID(), info.getGrouppriviledgeID(), info, langcode);



		}else {
			String key = userLoginServ.getkey(info.getPasswordEncy());
			String pass = userLoginServ.getencpassword(info.getPasswordEncy());

			info.setLoginkey(key);
			info.setPasswordEncy(pass);

			userLoginServ.check_username(info.getUsername(),langcode);
			userLoginServ.save(request,dg,info,langcode,Integer.parseInt(pagenum));
			System.out.println(Devicecode);
			String acct_no=accountServ.create_acct(Devicecode,username,usertokean,pagenum,request,dg,user.getUseridnumber(), info.getUsername(), langcode);
			System.out.println(acct_no);
			notificationServ.save("User "+info.getUsername()+" Created", info.getApplicationID(), info.getGrouppriviledgeID(), info, langcode);


		}
}else {
	String key = userLoginServ.getkey(info.getPasswordEncy());
	String pass = userLoginServ.getencpassword(info.getPasswordEncy());

	info.setLoginkey(key);
	info.setPasswordEncy(pass);

	userLoginServ.check_username(info.getUsername(),langcode);
	userLoginServ.save(request,dg,info,langcode,Integer.parseInt(pagenum));
	System.out.println(Devicecode);
	String acct_no=accountServ.create_acct(Devicecode,username,usertokean,pagenum,request,dg,user.getUseridnumber(), info.getUsername(), langcode);
	System.out.println(acct_no);
	notificationServ.save("User "+info.getUsername()+" Created", info.getApplicationID(), info.getGrouppriviledgeID(), info, langcode);


}



info.setPasswordEncy("");

return new ResponseEntity<UserLogin>(info, HttpStatus.OK);



}




@RequestMapping(value = "/profile/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<FilesUpload> saveprofilefile(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody threevalues input) {
 
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);


	System.out.println(input.getValue1() +" "+input.getValue2() +" "+input.getValue3());
	
	FilesUpload out= userServ.saveprofilefile(request,dg,input, langcode);
	
	 return new ResponseEntity<FilesUpload>(out, HttpStatus.OK);

	
}


@RequestMapping(value = "/file/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<FilesUpload> savefile(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody threevalues input) {
 
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);


	System.out.println(input.getValue1() +" "+input.getValue2() +" "+input.getValue3());
	
	FilesUpload out= userServ.savefile(request,dg,input, langcode);
	
	 return new ResponseEntity<FilesUpload>(out, HttpStatus.OK);

	
}



@RequestMapping(value = "/filebyuser/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<UserFile>> savefile(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Userobject input) {
 
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

	// This returns a JSON or XML with the users
//System.out.println("enter 1");
//System.out.println(input.getFirstName());
	System.out.println(input.getLogin().getId());
	
	List<UserFile> out= userFileServ.getfilebyuser(input.getLogin(), langcode);
	

	
	 return new ResponseEntity<List<UserFile>>(out, HttpStatus.OK);

	
}


@RequestMapping(value = "/deletefile/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<FilesUpload> deletefile(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestParam("fileid") String fileid,@RequestParam("object") String object,@RequestParam("component") String componentid) {
	
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

	
	// This returns a JSON or XML with the users
//System.out.println("enter 1");
//System.out.println(input.getFirstName());
	System.out.println(fileid+" "+ object+" "+componentid);
	
	FilesUpload out= userServ.deletefile(request,dg,fileid, object, componentid, langcode);
	
	 return new ResponseEntity<FilesUpload>(out, HttpStatus.OK);

	
}



	  
	  


}
