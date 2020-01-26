package com.rimdev.user.Controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.user.Services.DeviceOsServ;
import com.rimdev.user.Services.DeviceServ;
import com.rimdev.user.Services.DeviceStatusServ;
import com.rimdev.user.Services.DeviceTypeServ;
import com.rimdev.user.Services.PagesServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Device;
import com.rimdev.user.entities.Pages;
import com.rimdev.user.ouputobject.pagesdevice;
import com.rimdev.user.ouputobject.response_all;



@Controller // This means that this class is a Controller
@RequestMapping(path="/Device") // 
public class DeviceController {
	
	@Autowired
	DeviceServ deviceServ;
	
	@Autowired
	DeviceOsServ deviceOsServ;
	
	@Autowired
	DeviceTypeServ deviceTypeServ;
	
	@Autowired
	DeviceStatusServ deviceStatusServ;
	
	
	@Autowired
	PagesServ pagesServ;
	
	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Device>> getAllUsers(){
		return new ResponseEntity<List<Device>>(deviceServ.getall(), HttpStatus.OK);
	  }
	  
	  
	  @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
	  public  ResponseEntity<List<pagesdevice>> getpages(@PathVariable @NotNull int id){	  
		 List<pagesdevice> all=  pagesServ.getpagesbydevice(id);
		return new ResponseEntity<List<pagesdevice>>(all, HttpStatus.OK);
	  }
	  
	  


@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<response_all> saveorupdate(@RequestBody Device input) {
  // This returns a JSON or XML with the users
	response_all saveobject;
	
	if(input.getDeviceOSID()==null || input.getDeviceOSID().getId()==null) {
		System.out.println("here 1");
		input.setDeviceOSID(deviceOsServ.getbyname("Unknown"));
		
	}
	if(input.getDevicetypeID()==null || input.getDevicetypeID().getId()==null) {
		System.out.println("here 2");
		input.setDevicetypeID(deviceTypeServ.getbyname("Unknown"));
		
	}
	

	try {
		Device dev=deviceServ.checkdevice(input.getDeviceip(),input.getDeviceOSID(),input.getDevicetypeID(),input.getDevicebrowser()).get(0);
		
		System.out.println(deviceServ.checkdevice(input.getDeviceip(),input.getDeviceOSID(),input.getDevicetypeID(),input.getDevicebrowser()).size());


		//System.out.println(dev.getDevicename());
		if(dev != null ) {
			
			  BeanUtils.copyProperties(input, dev, ObjectUtils.getNullPropertyNames(input));
			  saveobject=deviceServ.Save(dev);
			  if(dev.getDevicestatusID().getId() == 2) {
				  saveobject.setStatus(1);
				  saveobject.setMessage("block device");
				  saveobject.setTokean(null);
				  saveobject.setExpiretime(null);
				  
			  }
	

			} else {
				input.setDevicestatusID(deviceStatusServ.getbyid(1));
				saveobject=deviceServ.Save(input);
			}
		
	} catch (Exception e) {
		// TODO: handle exception
		input.setDevicestatusID(deviceStatusServ.getbyid(1));
		saveobject=deviceServ.Save(input);
		

	}
	
	
	if(input.getPage() != null) {	
		try {
			Pages p= pagesServ.getbyid(input.getPage());
			pagesServ.savedevpag(saveobject.getDevice(), p);
		} catch (Exception e) {
			// TODO: handle exception
			saveobject.getDevice().setDevicestatusID(deviceStatusServ.getbyid(2));
			saveobject=deviceServ.Save(saveobject.getDevice());
			saveobject.setStatus(2);
		}

	}
	
	return new ResponseEntity<response_all>(saveobject, HttpStatus.OK);

	
}




@RequestMapping(value = "/block", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<response_all> block(@RequestBody Device input) {
  // This returns a JSON or XML with the users
	response_all saveobject = new response_all();
	


	try {
		Device dev=deviceServ.checkdevice(input.getDeviceip(),input.getDeviceOSID(),input.getDevicetypeID(),input.getDevicebrowser()).get(0);

		//System.out.println(dev.getDevicename());
		if(dev != null ) {
			  dev.setDevicestatusID(deviceStatusServ.getbyid(2));
			  BeanUtils.copyProperties(input, dev, ObjectUtils.getNullPropertyNames(input));
			  saveobject=deviceServ.Save(dev);

			}else {
				
				saveobject.setStatus(1);
			} 
		
	} catch (Exception e) {
		// TODO: handle exception
	
		saveobject.setStatus(1);

	}
	
	

	return new ResponseEntity<response_all>(saveobject, HttpStatus.OK);

	
}
	




@RequestMapping(value = "/unblock", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<response_all> unblock(@RequestBody Device input) {
  // This returns a JSON or XML with the users
	response_all saveobject = new response_all();
	


	try {
		Device dev=deviceServ.checkdevice(input.getDeviceip(),input.getDeviceOSID(),input.getDevicetypeID(),input.getDevicebrowser()).get(0);

		//System.out.println(dev.getDevicename());
		if(dev != null ) {
			  dev.setDevicestatusID(deviceStatusServ.getbyid(1));
			  BeanUtils.copyProperties(input, dev, ObjectUtils.getNullPropertyNames(input));
			  saveobject=deviceServ.Save(dev);

			}else {
				
				saveobject.setStatus(1);
			} 
		
	} catch (Exception e) {
		// TODO: handle exception
	
		saveobject.setStatus(1);

	}
	
	

	return new ResponseEntity<response_all>(saveobject, HttpStatus.OK);

	
}
	

}
