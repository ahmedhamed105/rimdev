package com.rimdev.accounting.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Repo.FlowTypeRepo;
import com.rimdev.accounting.Services.CurrencyServ;
import com.rimdev.accounting.Services.FlowTypeServ;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Flow") // 
public class FlowController {
	
	
	@Autowired
	FlowTypeServ flowTypeServ;
	
	
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<FlowType>> getAllUsers() {
	    // This returns a JSON or XML with the users
		
		return new ResponseEntity<List<FlowType>>(flowTypeServ.getall(), HttpStatus.OK);
	  }
	
	
	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<FlowType> saveorupdate(@RequestBody FlowType input) {
	    // This returns a JSON or XML with the users
		
		FlowType ouput = null;
		
		if(input.getId() == null || input.getId() == 0) {
			System.out.println("insert");
			try {
				 ouput= flowTypeServ.Save(input);
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setFlowdescription(ouput.getFlowdescription());
					 return new ResponseEntity<FlowType>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setFlowdescription(ouput.getFlowdescription());
					 return new ResponseEntity<FlowType>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			}
			
			return new ResponseEntity<FlowType>(ouput, HttpStatus.OK);

		}else {
			System.out.println("update "+input.getId());
			
			try {
				 ouput= flowTypeServ.update(input, input.getId());
				 if(ouput == null || ouput.getId() == -1) {
					
					 ouput.setFlowdescription(ouput.getFlowdescription());
					 return new ResponseEntity<FlowType>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null|| ouput.getId() == -1) {
					 
					 ouput.setFlowdescription(ouput.getFlowdescription());
					 return new ResponseEntity<FlowType>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			}
			
			return new ResponseEntity<FlowType>(ouput, HttpStatus.OK);
			
		}
		  }

}
