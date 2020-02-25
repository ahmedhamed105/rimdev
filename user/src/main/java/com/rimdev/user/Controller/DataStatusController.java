package com.rimdev.user.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.user.Repo.DataStatusRepo;
import com.rimdev.user.Services.AreaServ;
import com.rimdev.user.Services.DataStatusServ;
import com.rimdev.user.entities.Area;
import com.rimdev.user.entities.DataStatus;

@Controller // This means that this class is a Controller
@RequestMapping(path="/datastatus") // 
public class DataStatusController {
	
	
	@Autowired
	DataStatusServ dataStatusServ;

	  @RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<DataStatus>> getAllUsers(){
		return new ResponseEntity<List<DataStatus>>(dataStatusServ.getall(), HttpStatus.OK);
	  }
	  

}
