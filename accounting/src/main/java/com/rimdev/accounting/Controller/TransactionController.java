package com.rimdev.accounting.Controller;

import java.util.List;

import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.accounting.Repo.AccountProcessRepo;
import com.rimdev.accounting.Services.AccountProcessServ;
import com.rimdev.accounting.inputobject.transaction_input;
import com.rimdev.accounting.outputobject.Transaction_output;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Trx") // 
public class TransactionController {
	
	
	@Autowired
	AccountProcessServ  accountProcessServ;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<transaction_input> getAll() {
	    // This returns a JSON or XML with the users
		  
	
		  transaction_input ouput=new transaction_input();
		  
		  return new ResponseEntity<transaction_input>(ouput, HttpStatus.OK);
	  }
	
	
	
	@RequestMapping(value = "/POST", method = RequestMethod.POST)
	  public  ResponseEntity<Transaction_output> post(@RequestBody transaction_input input) {
	    // This returns a JSON or XML with the users
		  System.out.println(input);
			if(input.getAcct_no() == null && input.getCustomer_no() == null) {
				  Transaction_output ouput=new Transaction_output();
				  
				  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
				
			}else {
				
				if(input.getReference_no() == null) {
					
					int error =accountProcessServ.call_post(input.getAcct_no(), input.getCustomer_no());
					
					
					  Transaction_output ouput=new Transaction_output();
					ouput.setError_code(error);
					  
					  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.OK);	
					
				}else {
					Transaction_output ouput=new Transaction_output();
					ouput.setReference_no("reverse");
					  
					  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.OK);	
					
				}
				

			
				
			}
		
	  }

}
