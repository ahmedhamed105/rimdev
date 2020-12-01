package com.rimdev.rimpages.Controller;

import java.util.ArrayList;
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
import com.rimdev.rimpages.Services.DevicePageServ;
import com.rimdev.rimpages.Services.PagesServ;
import com.rimdev.rimpages.entities.DevicePage;
import com.rimdev.rimpages.entities.Pages;
import com.rimdev.rimpages.outputobject.pagesob;


@Controller // This means that this class is a Controller
@RequestMapping(path="/Page") // 
public class PagesController {
	
	@Autowired
	PagesServ pagesServ;
	
	@Autowired
	DevicePageServ devicePageServ;
	

	 
	 

		@RequestMapping(value = "/getbyid/{langcode}", method = RequestMethod.POST)
		public @ResponseBody ResponseEntity<Pages> getbyerrorcode(HttpServletRequest req, @RequestBody pagesob log,
				@PathVariable("langcode") String langcode) {

			try {

				Pages page = pagesServ.getbyid(log.getPageid(),langcode);

				return new ResponseEntity<Pages>(page, HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				// e.printStackTrace();
				throw e;
			}

		}
	 




}
