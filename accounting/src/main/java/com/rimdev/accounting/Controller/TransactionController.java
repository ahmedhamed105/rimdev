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
	
	
	
	@RequestMapping(value = "/POST", method = RequestMethod.POST)
	  public  ResponseEntity<Transaction_output> post(@RequestBody transaction_input input) {
	    // This returns a JSON or XML with the users
		  System.out.println(input);
		
		  Account acct =null;
				
	
					
					if(input.getAcct_no() == null && input.getCustomer_id() == null) {
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
								
								if(input.getAcct_no() != null && input.getCustomer_id() == null) {
								 acct = accountServ.getbyaccount(input.getAcct_no(), cur.getId());
									if(acct.getId() == -1) {
										ErrorCodes Err=	errorCodesServ.geterrordesc(1);
										  Transaction_output ouput=new Transaction_output();
										ouput.setError_code(Err.getErrorCode());
										ouput.setError_desc(Err.getErrordescription());
										  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);	
									}
								}
								if(input.getAcct_no() != null && input.getCustomer_id() != null) {
									acct= accountServ.getbyaccount(input.getAcct_no(), cur.getId());	
									if(acct.getId() == -1) {
										ErrorCodes Err=	errorCodesServ.geterrordesc(1);
										  Transaction_output ouput=new Transaction_output();
										ouput.setError_code(Err.getErrorCode());
										ouput.setError_desc(Err.getErrordescription());
										  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);	
									}
								}
								
								// check if Rim 
								
								if(input.getCustomer_id() != null && input.getAcct_no() == null) {
									 acct = accountServ.getbyRim(input.getCustomer_id(), cur.getId());	
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
					
				TransactionType trantype= TransactionTypeServ.checkpaymnet_not(input.getTrantypecode());
				if(trantype.getId() == -1 ) {
					
					ErrorCodes Err=	errorCodesServ.geterrordesc(6);
					  Transaction_output ouput=new Transaction_output();
					ouput.setError_code(Err.getErrorCode());
					ouput.setError_desc(Err.getErrordescription());
					  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
				
				
				}else {
					
					if(trantype.getPaymentNot() == 0) {
						ErrorCodes Err=	errorCodesServ.geterrordesc(8);
						  Transaction_output ouput=new Transaction_output();
						ouput.setError_code(Err.getErrorCode());
						ouput.setError_desc(Err.getErrordescription());
						  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
						
						
					}
					
					if(input.getAmount() == null || input.getAmount().compareTo(new BigDecimal("0.00")) == 0 ) {
						ErrorCodes Err=	errorCodesServ.geterrordesc(4);
						  Transaction_output ouput=new Transaction_output();
						ouput.setError_code(Err.getErrorCode());
						ouput.setError_desc(Err.getErrordescription());
						  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
						
					}else {
						
						String prouput =accountProcessServ.call_post(input.getReference_no(),input.getCurrency(),input.getAcct_no(),input.getAmount(),input.getTrantypecode(),input.getTrx_flow(),input.getTrx_desc(),input.getHold_id());
						int error = 1;
						String reference="no";
						if(prouput.contains(",")) {
							error=Integer.parseInt(prouput.split(",")[0]);
							reference=prouput.split(",")[1];
						}else {
							error=Integer.parseInt(prouput);
						}
						ErrorCodes Err=	errorCodesServ.geterrordesc(error);
						Transaction_output ouput=new Transaction_output();
						
						Account acctup = accountServ.findbyid(acct.getId());
						ouput.setReference_no(reference);
						ouput.setAmount(input.getAmount());
						ouput.setAvl_balance(acctup.getAvalbalance());
						ouput.setCurrent_balance(acctup.getCurrbalance());
						ouput.setCustomer_no(acctup.getCustomernumber());
						ouput.setAcct_no(acctup.getAcctNumber());
						ouput.setCurrency(acctup.getCurrencyID().getCurrencyISO());
						ouput.setError_code(Err.getErrorCode());
						ouput.setError_desc(Err.getErrordescription());
					return new ResponseEntity<Transaction_output>(ouput, HttpStatus.OK);	
									
						
						
						
					}
					
				}
				
		
				
					
		
	  }
	  
	  
	  @RequestMapping(value = "/Hold", method = RequestMethod.POST)
	  public  ResponseEntity<Transaction_output> hold(@RequestBody transaction_input input) {
	    // This returns a JSON or XML with the users
		  System.out.println(input);
		
		  Account acct =null;
				
	
					
					if(input.getAcct_no() == null && input.getCustomer_id() == null) {
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
								
								if(input.getAcct_no() != null && input.getCustomer_id() == null) {
								 acct = accountServ.getbyaccount(input.getAcct_no(), cur.getId());
									if(acct.getId() == -1) {
										ErrorCodes Err=	errorCodesServ.geterrordesc(1);
										  Transaction_output ouput=new Transaction_output();
										ouput.setError_code(Err.getErrorCode());
										ouput.setError_desc(Err.getErrordescription());
										  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);	
									}
								}
								if(input.getAcct_no() != null && input.getCustomer_id() != null) {
									acct= accountServ.getbyaccount(input.getAcct_no(), cur.getId());	
									if(acct.getId() == -1) {
										ErrorCodes Err=	errorCodesServ.geterrordesc(1);
										  Transaction_output ouput=new Transaction_output();
										ouput.setError_code(Err.getErrorCode());
										ouput.setError_desc(Err.getErrordescription());
										  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);	
									}
								}
								
								// check if Rim 
								
								if(input.getCustomer_id() != null && input.getAcct_no() == null) {
									 acct = accountServ.getbyRim(input.getCustomer_id(), cur.getId());	
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
					
				TransactionType trantype= TransactionTypeServ.checkpaymnet_not(input.getTrantypecode());
				if(trantype.getId() == -1 ) {
					
					ErrorCodes Err=	errorCodesServ.geterrordesc(6);
					  Transaction_output ouput=new Transaction_output();
					ouput.setError_code(Err.getErrorCode());
					ouput.setError_desc(Err.getErrordescription());
					  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
				
				
				}else {
					
					if(trantype.getPaymentNot() == 1) {
						ErrorCodes Err=	errorCodesServ.geterrordesc(8);
						  Transaction_output ouput=new Transaction_output();
						ouput.setError_code(Err.getErrorCode());
						ouput.setError_desc(Err.getErrordescription());
						  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
						
						
					}
					
					if(input.getAmount() == null || input.getAmount().compareTo(new BigDecimal("0.00")) == 0 ) {
						ErrorCodes Err=	errorCodesServ.geterrordesc(4);
						  Transaction_output ouput=new Transaction_output();
						ouput.setError_code(Err.getErrorCode());
						ouput.setError_desc(Err.getErrordescription());
						  return new ResponseEntity<Transaction_output>(ouput, HttpStatus.BAD_REQUEST);
						
					}else{
						
						// hold call procedure
						
						String prouput =accountProcessServ.call_post(input.getReference_no(),input.getCurrency(),input.getAcct_no(),input.getAmount(),input.getTrantypecode(),input.getTrx_flow(),input.getTrx_desc(),input.getHold_id());
						int error = 1;
						String reference="no";
						if(prouput.contains(",")) {
							error=Integer.parseInt(prouput.split(",")[0]);
							reference=prouput.split(",")[1];
						}else {
							error=Integer.parseInt(prouput);
						}
						ErrorCodes Err=	errorCodesServ.geterrordesc(error);
						Transaction_output ouput=new Transaction_output();
						
						Account acctup = accountServ.findbyid(acct.getId());
						ouput.setReference_no(reference);
						ouput.setAmount(input.getAmount());
						ouput.setAvl_balance(acctup.getAvalbalance());
						ouput.setCurrent_balance(acctup.getCurrbalance());
						ouput.setCustomer_no(acctup.getCustomernumber());
						ouput.setAcct_no(acctup.getAcctNumber());
						ouput.setCurrency(acctup.getCurrencyID().getCurrencyISO());
						ouput.setError_code(Err.getErrorCode());
						ouput.setError_desc(Err.getErrordescription());
					return new ResponseEntity<Transaction_output>(ouput, HttpStatus.OK);	
									
						
						
						
					}
					
				}
				
		
				
					
		
	  }

}
