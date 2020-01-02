package com.rimdev.accounting.Controller;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Enttities.ErrorCodes;
import com.rimdev.accounting.Enttities.TransactionType;
import com.rimdev.accounting.Repo.AccountProcessRepo;
import com.rimdev.accounting.Services.AccountProcessServ;
import com.rimdev.accounting.Services.AccountServ;
import com.rimdev.accounting.Services.CurrencyServ;
import com.rimdev.accounting.Services.ErrorCodesServ;
import com.rimdev.accounting.Services.TransactionTypeServ;
import com.rimdev.accounting.inputobject.transaction_input;
import com.rimdev.accounting.outputobject.Transaction_output;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Trx") // 
public class TransactionController {
	
	
	@Autowired
	AccountProcessServ  accountProcessServ;
	
	@Autowired
	ErrorCodesServ errorCodesServ;
	
	@Autowired
	TransactionTypeServ TransactionTypeServ;
	
	@Autowired
	CurrencyServ currencyServ;
	
	@Autowired
	AccountServ accountServ;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	  public  ResponseEntity<transaction_input> getAll() {
	    // This returns a JSON or XML with the users
		  
	
		  transaction_input ouput=new transaction_input();
		  
		  return new ResponseEntity<transaction_input>(ouput, HttpStatus.OK);
	  }
	
	
	
	@RequestMapping(value = "/Credit", method = RequestMethod.POST)
	  public  ResponseEntity<Transaction_output> post(@RequestBody transaction_input input) {
	    // This returns a JSON or XML with the users
		  System.out.println(input);
		

				
				if(input.getReference_no() == null) {
					
					
					if(input.getAcct_no() == null && input.getCustomer_no() == null) {
						ErrorCodes Err=	errorCodesServ.geterrordesc(1);
						  Transaction_output ouput=new Transaction_output();
						ouput.setError_code(Err.getErrorCode());
						ouput.setError_desc(Err.getErrordescription());
						  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
						
					}else {
						
						if(input.getCurrency() == null) {
							ErrorCodes Err=	errorCodesServ.geterrordesc(3);
							  Transaction_output ouput=new Transaction_output();
							ouput.setError_code(Err.getErrorCode());
							ouput.setError_desc(Err.getErrordescription());
							  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
						}else {
							
							//check currency 
							Currency cur=currencyServ.check_currency(input.getCurrency());
							if(cur.getId() == -1) {
								
								ErrorCodes Err=	errorCodesServ.geterrordesc(3);
								  Transaction_output ouput=new Transaction_output();
								ouput.setError_code(Err.getErrorCode());
								ouput.setError_desc(Err.getErrordescription());
								  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
							
							
							}else {
								
								
								// check if account 
								
								if(input.getAcct_no() != null && input.getCustomer_no() == null) {
								Account acct = accountServ.getbyaccount(input.getAcct_no(), cur.getId());
									if(acct.getId() == -1) {
										ErrorCodes Err=	errorCodesServ.geterrordesc(1);
										  Transaction_output ouput=new Transaction_output();
										ouput.setError_code(Err.getErrorCode());
										ouput.setError_desc(Err.getErrordescription());
										  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);	
									}
								}
								if(input.getAcct_no() != null && input.getCustomer_no() != null) {
									Account acct = accountServ.getbyaccount(input.getAcct_no(), cur.getId());	
									if(acct.getId() == -1) {
										ErrorCodes Err=	errorCodesServ.geterrordesc(1);
										  Transaction_output ouput=new Transaction_output();
										ouput.setError_code(Err.getErrorCode());
										ouput.setError_desc(Err.getErrordescription());
										  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);	
									}
								}
								
								// check if Rim 
								
								if(input.getCustomer_no() != null && input.getAcct_no() == null) {
									Account acct = accountServ.getbyRim(input.getCustomer_no(), cur.getId());	
									if(acct.getId() == -1) {
										ErrorCodes Err=	errorCodesServ.geterrordesc(2);
										  Transaction_output ouput=new Transaction_output();
										ouput.setError_code(Err.getErrorCode());
										ouput.setError_desc(Err.getErrordescription());
										  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);	
									}
									
									input.setAcct_no(acct.getAcctNumber());
								}
							}
						}
					}
					// amonut not zero while debit or credit by paymnet_not in transaction type table 
					
				boolean paymnet=TransactionTypeServ.checkpaymnet_not(input.getTrx_type());
				if(paymnet && (input.getAmount() == null || input.getAmount().compareTo(new BigDecimal("0.00")) == 0)) {
					ErrorCodes Err=	errorCodesServ.geterrordesc(4);
					  Transaction_output ouput=new Transaction_output();
					ouput.setError_code(Err.getErrorCode());
					ouput.setError_desc(Err.getErrordescription());
					  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
					
				}
	
					
		int error =accountProcessServ.call_post(input.getCustomer_no(),input.getCurrency(),input.getAcct_no(),input.getAmount(),input.getTrx_type(),input.getTrx_flow());
		ErrorCodes Err=	errorCodesServ.geterrordesc(error);
					  Transaction_output ouput=new Transaction_output();
					ouput.setError_code(Err.getErrorCode());
					ouput.setError_desc(Err.getErrordescription());
					  
					  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.OK);	
					
				}else {
					// reverse with refernece
					
					Transaction_output ouput=new Transaction_output();
					ouput.setReference_no("reverse");
					  
					  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.OK);	
					
				}
				
				
					
		
	  }

}
