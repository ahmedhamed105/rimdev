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

import com.rimdev.accounting.Enttities.TransactionType;
import com.rimdev.accounting.Services.TransactionTypeServ;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Trxtype") // 
public class TrxtypeController {
	
	

	@Autowired
	TransactionTypeServ TransactionTypeServ;
	
	
	
	
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<List<TransactionType>> getAllUsers() {
	    // This returns a JSON or XML with the users
		
		return new ResponseEntity<List<TransactionType>>(TransactionTypeServ.getall(), HttpStatus.OK);
	  }
	
	
	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<TransactionType> saveorupdate(@RequestBody TransactionType input) {
	    // This returns a JSON or XML with the users
		
		TransactionType ouput = null;
		
		if(input.getId() == null || input.getId() == 0) {
			System.out.println("insert");
			try {
				 ouput= TransactionTypeServ.Save(input);
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<TransactionType>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<TransactionType>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			}
			
			return new ResponseEntity<TransactionType>(ouput, HttpStatus.OK);

		}else {
			System.out.println("update "+input.getId());
			
			try {
				 ouput= TransactionTypeServ.update(input, input.getId());
				 if(ouput == null || ouput.getId() == -1) {
					
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<TransactionType>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null|| ouput.getId() == -1) {
					 
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<TransactionType>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			}
			
			return new ResponseEntity<TransactionType>(ouput, HttpStatus.OK);
			
		}
		  }

}
