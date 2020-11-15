package com.rimdev.rimdevices.Controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
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
import com.rimdev.rimdevices.entities.Deviceip;
import com.rimdev.rimdevices.entities.Notification;
import com.rimdev.rimdevices.outputobject.loginpra;

@Controller // This means that this class is a Controller
@RequestMapping(path="/DeviceCheck") // 
public class DeviceCheckController {
	
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
	
	

@RequestMapping(value = "/DevicePage/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<loginpra> DevicePage(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Device input) 
		throws IOException, GeoIp2Exception {


input.setDeviceip(request.getRemoteAddr());

Deviceip geo=getip(request.getRemoteAddr());


Device out=null;
DevicePage  out1=null;

	
		Device dev=deviceServ.checkdevicetwo(input.getDevicecode(),langcode);
		
	
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



}
