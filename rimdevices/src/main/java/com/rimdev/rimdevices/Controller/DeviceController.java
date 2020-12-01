package com.rimdev.rimdevices.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.rimdevices.Exception.NoResultException;
import com.rimdev.rimdevices.Services.DeviceServ;
import com.rimdev.rimdevices.Services.LangExternalServ;
import com.rimdev.rimdevices.entities.Device;
import com.rimdev.rimdevices.outputobject.Deviceob;


@Controller // This means that this class is a Controller
@RequestMapping(path="/Device") // 
public class DeviceController {
	
	@Autowired
	DeviceServ deviceServ;
	
	@Autowired
	LangExternalServ textConvertionServ;
	
	


@RequestMapping(value = "/search/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Device> getbyid(HttpServletRequest req, @RequestBody Deviceob dev,@PathVariable("langcode") String langcode) {

	Device outdevice= null;
	
	try {
		if(dev.getData().getId() != null) {
			 outdevice=deviceServ.getbyid(dev.getData().getId(), langcode);

		}else if(dev.getData().getDevicecode() != null) {
			
			outdevice=deviceServ.getbycode(dev.getData().getDevicecode(), langcode);
		}else {
			throw new NoResultException(textConvertionServ.search("E104", langcode));
		}

		

return new ResponseEntity<Device>(outdevice, HttpStatus.OK);
	} catch (Exception e) {
		throw e;
	}

}


@RequestMapping(value = "/saveorupdate/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Device> saveorupdate(HttpServletRequest req, @RequestBody Deviceob dev,@PathVariable("langcode") String langcode) {
	Device outdevice;
	try {
		 outdevice = getbyid(req, dev, langcode).getBody();
	//	System.out.println(outdevice.getId());
	} catch (Exception e) {
		e.printStackTrace();
		Device out = deviceServ.saveDevice(dev.getData(), langcode);
		return new ResponseEntity<Device>(out, HttpStatus.OK);
	}
	try {
		Device out = deviceServ.updateDevice(outdevice, dev.getData(), langcode);
		return new ResponseEntity<Device>(out, HttpStatus.OK);
	} catch (Exception e) {
		// TODO: handle exception
		throw e;
	}
	
	

}



@RequestMapping(value = "/savedevicepage/{langcode}", method = RequestMethod.POST)
public @ResponseBody ResponseEntity<Device> savedevicepage(HttpServletRequest req, @RequestBody Deviceob dev,@PathVariable("langcode") String langcode) {
	return null;
	
	
	

}


	
	
	
	

}
