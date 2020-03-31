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
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.user.Services.DeviceOsServ;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.DeviceServ;
import com.rimdev.user.Services.DeviceStatusServ;
import com.rimdev.user.Services.DeviceTypeServ;
import com.rimdev.user.Services.PagesServ;
import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.entities.User;
import com.rimdev.user.entities.UserLogin;
import com.rimdev.user.ouputobject.pagesdevice;
import com.rimdev.user.ouputobject.response_all;



@Controller // This means that this class is a Controller
@RequestMapping(path="/Device") // 
public class DeviceController {
	
	@Autowired
	DeviceServ deviceServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	PagesServ pagesServ;
	
	@Autowired
	UserLoginServ userLoginServ;
	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Device>> getAll( @RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode){
		  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

		  return new ResponseEntity<List<Device>>(deviceServ.getall(langcode), HttpStatus.OK);
	  }
	  
	  
	 
	  public  ResponseEntity<List<Device>> getAll( String langcode){
		  return new ResponseEntity<List<Device>>(deviceServ.getall(langcode), HttpStatus.OK);
	  }
	  
	  
	  @RequestMapping(value = "/page/{langcode}/{deviceid}", method = RequestMethod.GET)
	  public  ResponseEntity<List<pagesdevice>> getpages( @RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@PathVariable("deviceid")  int deviceid){	  
		  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

		  
		  return new ResponseEntity<List<pagesdevice>>(pagesServ.getpagesbydevice(deviceid,langcode), HttpStatus.OK);
	  }
	  
	  


@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Device>> saveorupdate( @RequestHeader("Devicetokean") String  Devicetokean,@RequestHeader("pageid") String  pageid,@PathVariable("langcode") String langcode,@RequestBody Device input) {
  // This returns a JSON or XML with the users
	  DevicePage a= devicePageServ.check_tokean_page(Devicetokean, pageid, langcode);

	Device out=null;
	try {

		Device dev=deviceServ.checkdevice(input.getDeviceip(),input.getDeviceOSID(),input.getDevicetypeID(),input.getDevicebrowser());
		
		System.out.println(deviceServ.checkdevice(input.getDeviceip(),input.getDeviceOSID(),input.getDevicetypeID(),input.getDevicebrowser()));


		//System.out.println(dev.getDevicename());
		if(dev != null ) {		
			  BeanUtils.copyProperties(input, dev, ObjectUtils.getNullPropertyNames(input));
			  out=deviceServ.update(dev,langcode);
	

			} else {
				out=deviceServ.Save(input,langcode);
			}
		
	} catch (Exception e) {
		// TODO: handle exception
		
		out=deviceServ.Save(input,langcode);
		

	}
	
	

	
	return getAll(langcode);

	
}


@RequestMapping(value = "/DevicePage/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Device> DevicePage(@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@PathVariable("langcode") String langcode,@RequestBody Device input) {
  // This returns a JSON or XML with the users
System.out.println(username + " "+usertokean);

UserLogin a= userLoginServ.getbyusernametokean(username, usertokean, langcode);

	System.out.println("inserted page "+input.getDevicecode());
	
	Device out=null;
	try {
		
	
		Device dev=deviceServ.checkdevicetwo(input.getDevicecode());
		if(dev == null) {
	     dev=deviceServ.checkdevice(input.getDeviceip(),input.getDeviceOSID(),input.getDevicetypeID(),input.getDevicebrowser());
		}
		
		

		//System.out.println(dev.getDevicename());
		if(dev != null ) {		
			  BeanUtils.copyProperties(input, dev, ObjectUtils.getNullPropertyNames(input));
			  out=deviceServ.updateDP(dev,username, usertokean,langcode);
	

			} else {
				out=deviceServ.SaveDP(input,username, usertokean,langcode);
			}
		
	} catch (Exception e) {
		// TODO: handle exception
		
		out=deviceServ.SaveDP(input,username, usertokean,langcode);
		

	}
	
	

	return new ResponseEntity<Device>(out, HttpStatus.OK);


	
}


	

}
