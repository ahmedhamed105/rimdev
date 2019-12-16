package com.rimdev.RES.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rimdev.RES.Service.DevicetypeServ;
import com.rimdev.RES.Service.devicetableServ;
import com.rimdev.RES.entites.Device_type;
import com.rimdev.RES.entites.Device_type;
import com.rimdev.RES.output.DevToken;

@RestController
@RequestMapping(value = "/Device/devicetype")
public class DeviceTypeCon {
	
	@Autowired
	DevicetypeServ devicetypeServ;
	
	
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Device_type>> alldev() {
		
		List<Device_type> paramters=new ArrayList<Device_type>();
	
		try {
			paramters=devicetypeServ.selectall();
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Device_type>>(paramters, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Device_type>>(paramters, HttpStatus.OK);

		

	}
	
	
	@RequestMapping(value = "/addorupdate", method = RequestMethod.POST)
	public ResponseEntity<List<Device_type>> add_parameter(@RequestBody List<Device_type> paramtersinput) {
		
		try {
			boolean ok_not=devicetypeServ.addorupdate(paramtersinput);
			
			if(!ok_not) return new ResponseEntity<List<Device_type>>(paramtersinput, HttpStatus.FORBIDDEN);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Device_type>>(paramtersinput, HttpStatus.BAD_REQUEST);
		}
		
		
		
		List<Device_type> paramters=new ArrayList<Device_type>();
	
		try {
			paramters=devicetypeServ.selectall();
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Device_type>>(paramters, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Device_type>>(paramters, HttpStatus.OK);

		

	}

}
