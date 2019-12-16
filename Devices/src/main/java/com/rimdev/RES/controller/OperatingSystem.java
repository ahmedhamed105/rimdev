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

import com.rimdev.RES.Service.OperaSystemServ;
import com.rimdev.RES.Service.devicetableServ;
import com.rimdev.RES.entites.OperaSystem;
import com.rimdev.RES.output.DevToken;

@RestController
@RequestMapping(value = "/Device/Os")
public class OperatingSystem {
	
	@Autowired
	OperaSystemServ operaSystemServ;
	
	
	
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public ResponseEntity<List<OperaSystem>> alldev() {
		
		List<OperaSystem> paramters=new ArrayList<OperaSystem>();
	
		try {
			paramters=operaSystemServ.selectall();
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<OperaSystem>>(paramters, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<OperaSystem>>(paramters, HttpStatus.OK);

		

	}
	
	
	@RequestMapping(value = "/addorupdate", method = RequestMethod.POST)
	public ResponseEntity<List<OperaSystem>> add_parameter(@RequestBody List<OperaSystem> paramtersinput) {
		
		try {
			boolean ok_not=operaSystemServ.addorupdate(paramtersinput);
			
			if(!ok_not) return new ResponseEntity<List<OperaSystem>>(paramtersinput, HttpStatus.FORBIDDEN);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<OperaSystem>>(paramtersinput, HttpStatus.BAD_REQUEST);
		}
		
		
		
		List<OperaSystem> paramters=new ArrayList<OperaSystem>();
	
		try {
			paramters=operaSystemServ.selectall();
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<List<OperaSystem>>(paramters, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<OperaSystem>>(paramters, HttpStatus.OK);

		

	}

}
