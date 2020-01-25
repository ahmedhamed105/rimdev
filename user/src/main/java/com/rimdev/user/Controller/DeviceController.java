package com.rimdev.user.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.user.Services.DeviceOsServ;
import com.rimdev.user.Services.DeviceServ;
import com.rimdev.user.Services.DeviceTypeServ;
import com.rimdev.user.Utils.ObjectUtils;
import com.rimdev.user.entities.Device;
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
	
	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<Device>> getAllUsers(){
		return new ResponseEntity<List<Device>>(deviceServ.getall(), HttpStatus.OK);
	  }
	  
	  


@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<response_all> saveorupdate(@RequestBody Device input) {
  // This returns a JSON or XML with the users
	
	
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

		return new ResponseEntity<response_all>(deviceServ.Save(dev), HttpStatus.OK);

			} else
			return new ResponseEntity<response_all>(deviceServ.Save(input), HttpStatus.OK);	

		
	} catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<response_all>(deviceServ.Save(input), HttpStatus.OK);

	}
	
}
	

}
