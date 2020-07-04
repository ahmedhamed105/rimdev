package com.rimdev.user.Controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
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
import com.rimdev.user.Exception.BlockedException;
import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Services.DevicePageServ;
import com.rimdev.user.Services.DeviceServ;
import com.rimdev.user.Services.DeviceStatusServ;
import com.rimdev.user.Services.DeviceipServ;
import com.rimdev.user.Services.GroupPagesServ;
import com.rimdev.user.Services.GroupWebServ;
import com.rimdev.user.Services.LogServ;
import com.rimdev.user.Services.NotificationServ;
import com.rimdev.user.Services.PagesServ;
import com.rimdev.user.Services.TextConvertionServ;
import com.rimdev.user.Services.UserLoginServ;
import com.rimdev.user.Services.WebservicepriviledgeServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.DeviceStatus;
import com.rimdev.user.entities.Deviceip;
import com.rimdev.user.entities.GroupPriviledge;
import com.rimdev.user.entities.GroupWeb;
import com.rimdev.user.entities.Notification;
import com.rimdev.user.entities.UserLogin;
import com.rimdev.user.ouputobject.loginpra;
import com.rimdev.user.ouputobject.pagesdevice;

import javassist.NotFoundException;


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


public Deviceip getip(String ip) {
	
	Deviceip a= new Deviceip();	
	a.setIpAddress(ip);
	
	try {
		File database  = new File(
				getClass().getClassLoader().getResource("GeoLite2-City.mmdb").getFile()
			);


			DatabaseReader dbReader = new DatabaseReader.Builder(database).build();


		InetAddress ipAddress = InetAddress.getByName(ip);
		CityResponse response = dbReader.city(ipAddress);
		     
		String countryName = response.getCountry().getName();
		a.setCountry(countryName);
		String cityName = response.getCity().getName();
		a.setCity(cityName);
		String state = response.getLeastSpecificSubdivision().getName();
		a.setState(state);

		String latitude = 
		response.getLocation().getLatitude().toString();

		a.setLatitude(latitude);

		String longitude = 
		response.getLocation().getLongitude().toString();
		a.setLongitude(longitude);

		String timezone = 
		response.getLocation().getTimeZone().toString();
		a.setTimezone(timezone);


		String subneting = 
		response.getTraits().getNetwork().toString();
		a.setSubneting(subneting);

		
	} catch (Exception e) {
		// TODO: handle exception
		
	
	}
	

		return a;
	
	}




@RequestMapping(value = "/DevicePage/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<loginpra> DevicePage(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Device input) 
		throws IOException, GeoIp2Exception {


input.setDeviceip(request.getRemoteAddr());

Deviceip geo=getip(request.getRemoteAddr());


Device out=null;
DevicePage  out1=null;

	
		Device dev=deviceServ.checkdevicetwo(input.getDevicecode(),langcode);
		
		//System.out.println(dev.getDevicename());
		if(dev != null ) {
	
			  out1=deviceServ.updateDP(request,dev,input,username, usertokean,langcode);
			} else {
				  out1=deviceServ.SaveDP(request,input,username, usertokean,langcode);
			
			}

	out =out1.getDeviceId();
	geo.setDeviceId(out);
	deviceipServ.savebyip(geo, langcode);
	
	
	GroupPagesServ.check_page(request,out1, langcode);
	loginpra outlogin = new loginpra();
	outlogin.setUsername(out1.getUserloginID().getUsername());
	outlogin.setUsertokean(out1.getPageTokean());
	outlogin.setUserlogin(out1.getUserloginID());
	outlogin.setUserid(out1.getUserloginID().getUserID());
	outlogin.setApp(out1.getUserloginID().getApplicationID());
	List<Notification> notif= notificationServ.getbygroup(out1.getUserloginID().getGrouppriviledgeID().getId());
	outlogin.setNotif(notif);

	return new ResponseEntity<loginpra>(outlogin, HttpStatus.OK);


	
}


	

}
