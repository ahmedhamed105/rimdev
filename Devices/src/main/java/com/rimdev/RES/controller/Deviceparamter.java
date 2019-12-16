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

import com.rimdev.RES.Service.devicetableServ;
import com.rimdev.RES.entites.Device_table;
import com.rimdev.RES.output.DevToken;

@RestController
@RequestMapping(value = "/Device/paramter")
public class Deviceparamter {
	
	@Autowired
	devicetableServ devicetableserv;
	
	
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<Device_table>> alldev() {
		
		List<Device_table> paramters=new ArrayList<Device_table>();
	
		try {
			paramters=devicetableserv.selectall();
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Device_table>>(paramters, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Device_table>>(paramters, HttpStatus.OK);

		

	}
	
	
	@RequestMapping(value = "/addorupdate", method = RequestMethod.POST)
	public ResponseEntity<List<Device_table>> add_parameter(@RequestBody List<Device_table> paramtersinput) {
		
		try {
			boolean ok_not=devicetableserv.addorupdate(paramtersinput);
			
			if(!ok_not) return new ResponseEntity<List<Device_table>>(paramtersinput, HttpStatus.FORBIDDEN);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Device_table>>(paramtersinput, HttpStatus.BAD_REQUEST);
		}
		
		
		
		List<Device_table> paramters=new ArrayList<Device_table>();
	
		try {
			paramters=devicetableserv.selectall();
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<Device_table>>(paramters, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Device_table>>(paramters, HttpStatus.OK);

		

	}

}
