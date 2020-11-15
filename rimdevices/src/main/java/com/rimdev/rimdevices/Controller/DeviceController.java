package com.rimdev.rimdevices.Controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.rimdev.rimdevices.Exception.PopupException;
import com.rimdev.rimdevices.Services.DevicePageServ;
import com.rimdev.rimdevices.Services.DeviceServ;
import com.rimdev.rimdevices.Services.DeviceStatusServ;
import com.rimdev.rimdevices.Services.DeviceipServ;
import com.rimdev.rimdevices.Services.GroupPagesServ;
import com.rimdev.rimdevices.Services.GroupWebServ;
import com.rimdev.rimdevices.Services.LogServ;
import com.rimdev.rimdevices.Services.NotificationServ;
import com.rimdev.rimdevices.Services.PagesServ;
import com.rimdev.rimdevices.Services.TextConvertionServ;
import com.rimdev.rimdevices.Services.UserLoginServ;
import com.rimdev.rimdevices.entities.Device;
import com.rimdev.rimdevices.entities.DevicePage;
import com.rimdev.rimdevices.entities.DeviceStatus;
import com.rimdev.rimdevices.entities.Deviceip;
import com.rimdev.rimdevices.entities.Notification;
import com.rimdev.rimdevices.outputobject.loginpra;
import com.rimdev.rimdevices.outputobject.pagesdevice;


@Controller // This means that this class is a Controller
@RequestMapping(path="/Device") // 
public class DeviceController {
	
	@Autowired
	DeviceServ deviceServ;
	
	
	@Autowired
	PagesServ pagesServ;
	
	@Autowired
	UserLoginServ userLoginServ;
	
	@Autowired
	DeviceipServ deviceipServ;
	
	@Autowired
	GroupWebServ groupWebServ;
	
	@Autowired
	GroupPagesServ GroupPagesServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	
	@Autowired
	LogServ logServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	NotificationServ notificationServ;
	
	@Autowired
	DeviceStatusServ deviceStatusServ;

	
	  @RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
	  public  ResponseEntity<List<Device>> getAll( HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
		  List<String> paramter =new ArrayList<String>();
		  List<String> values =new ArrayList<String>();
		  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

		    return ResponseEntity.ok()
		    	      .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
		    	      .body(deviceServ.getall(langcode));
	  }
	  
	  
	 

	  
	  @RequestMapping(value = "/page/{langcode}", method = RequestMethod.POST)
	  public  ResponseEntity<List<pagesdevice>> getpages(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Device input){	  
		
		  List<String> paramter =new ArrayList<String>();
		  List<String> values =new ArrayList<String>();
		  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);

		  return new ResponseEntity<List<pagesdevice>>(devicePageServ.getpagesbydevice(input.getId(),langcode), HttpStatus.OK);
	  }
	  
	  


@RequestMapping(value = "/update/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<List<Device>> saveorupdate(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Device input) {
  // This returns a JSON or XML with the users
	  List<String> paramter =new ArrayList<String>();
	  List<String> values =new ArrayList<String>();
	  DevicePage a= devicePageServ.check_webservice(request, usertokean, username, pagenum, langcode,Devicecode,paramter,values);
	  Device user = null;
	  if (input.getId() == null) {

			throw new PopupException("error while updating");

		} else {

			try {
				user = deviceServ.getbyid(input.getId(), langcode);
				// System.out.println("enter 2");

				if (user == null) {
					// System.out.println("enter 3");
					throw new PopupException("error while updating");

				} else {
					
				DeviceStatus devicestatusID= deviceStatusServ.getbyid(input.getDevicestatusID().getId());
				input.setDevicestatusID(devicestatusID);
				user = deviceServ.update(user, input, langcode);

				}
			} catch (Exception e) {
				// TODO: handle exception

				throw new PopupException("error while updating");
			}

		}

		return getAll(request, Devicecode, username, usertokean, pagenum, langcode);
	
	
}




	

}
