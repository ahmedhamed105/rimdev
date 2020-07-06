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
import com.rimdev.user.Services.DataStatusServ;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.TelephonesServ;
import com.rimdev.user.Services.UserServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.DataStatus;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Email;
import com.rimdev.user.entities.Telephones;
import com.rimdev.user.ouputobject.Emailobj;
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
	
	@Autowired
	DataStatusServ  dataStatusServ;
	
	
	  @RequestMapping(value = "/primary/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<select_object>> getprimary(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
	
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage dg= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	  
	  
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
	  public  ResponseEntity<List<Emailobj>> getAll(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
		  //exception handled
		return new ResponseEntity<List<Emailobj>>(telephonesServ.getall(langcode), HttpStatus.OK);
	  }
	  

	  
	  @RequestMapping(value = "/user/{langcode}/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Telephones>> getUsersbyuser(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@PathVariable("id") int userid){ 
		  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>(); 
	  paramter.add("id");
	  values.add(String.valueOf(userid));
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
		  //exception handled
	    return new ResponseEntity<List<Telephones>>(telephonesServ.getbyuserlogin(userid,langcode), HttpStatus.OK);

		
	  }
	  
	  


@RequestMapping(value = "/save/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Emailobj>> save(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Emailobj teles) {
  // This returns a JSON or XML with the users
	
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
		try {
			if(teles.getTele().getId() == null || teles.getTele().getDatastatusID().getId() != null) {
				
				telephonesServ.check_tele(teles.getTele().getPhoneNo(),langcode);
		              DataStatus status = dataStatusServ.getbyid(teles.getTele().getDatastatusID().getId(), langcode);
		              teles.getTele().setDatastatusID(status);
		              telephonesServ.save(teles,langcode);
			} else {
				throw new PopupException("error while insertion 1");
			}
		} catch (PopupException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
	
			throw new PopupException("error while insertion");
		}
		
			
		return getAll(request, Devicecode, username, usertokean, pagenum, langcode);

}




@RequestMapping(value = "/update/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Emailobj>> update(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Emailobj teles) {
  // This returns a JSON or XML with the users
	
	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

if (teles.getUserid() == null || teles.getTele().getId() == null || teles.getTele().getDatastatusID().getId() == null) {

	throw new PopupException("error while updating");

} else {
	try {
	
			Telephones found= telephonesServ.getbyid(teles.getTele().getId(),langcode);
			
			if(found != null ) {

              if(!found.getPhoneNo().equals(teles.getTele().getPhoneNo())) {
            	  telephonesServ.check_tele(teles.getTele().getPhoneNo(),langcode);
              }
              
              DataStatus status = dataStatusServ.getbyid(teles.getTele().getDatastatusID().getId(), langcode);
              teles.getTele().setDatastatusID(status);
              telephonesServ.update(found,teles,langcode);

			}

	} catch (PopupException e) {
		// TODO: handle exception
		throw e;
	} catch (Exception e) {
		// TODO: handle exception

		throw new PopupException("error while updating");
	}
		
}
return getAll(request, Devicecode, username, usertokean, pagenum, langcode);
}



@RequestMapping(value = "/delete/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Emailobj>> delete(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Emailobj teles) {
  // This returns a JSON or XML with the users

	  List<String> paramter =new ArrayList<String>();
List<String> values =new ArrayList<String>();
DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

if (teles.getUserid() == null || teles.getTele().getId() == null || teles.getTele().getDatastatusID().getId() == null) {

	throw new PopupException("error while updating");

} else {
	try {
	
		Telephones found= telephonesServ.getbyid(teles.getTele().getId(),langcode);
			
			if(found != null ) {

            
            telephonesServ.delete(found,langcode);

			}

	} catch (PopupException e) {
		// TODO: handle exception
		throw e;
	} catch (Exception e) {
		// TODO: handle exception

		throw new PopupException("error while updating");
	}
		
}
return getAll(request, Devicecode, username, usertokean, pagenum, langcode);
}



}
