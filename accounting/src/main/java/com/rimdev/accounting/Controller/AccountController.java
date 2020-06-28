package com.rimdev.accounting.Controller;

import java.math.BigDecimal;
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

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.ErrorCodes;
import com.rimdev.accounting.Exception.PopupException;
import com.rimdev.accounting.Services.AccountProcessServ;
import com.rimdev.accounting.Services.AccountServ;
import com.rimdev.accounting.Services.ErrorCodesServ;
import com.rimdev.accounting.Services.ExternalServ;
import com.rimdev.accounting.Services.TextConvertionServ;
import com.rimdev.accounting.inputobject.Acct_obj;
import com.rimdev.accounting.inputobject.transfer_obj;
import com.rimdev.accounting.outputobject.Transaction_output;
import com.rimdev.accounting.outputobject.textobject;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Account") // 
public class AccountController {
	
	@Autowired
	AccountServ accountServ;
	
	@Autowired
	AccountProcessServ accountProcessServ;
	
	@Autowired
	ExternalServ externalServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	ErrorCodesServ errorCodesServ;
	
	
	

	
	@RequestMapping(value = "/transfer/{langcode}", method = RequestMethod.POST)
	  public  @ResponseBody Transaction_output account_transfer(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody transfer_obj acct) {
	    // This returns a JSON or XML with the users
		Account from;
		Account to;
		BigDecimal amount;
		
	
		
		try {
		//	System.out.println(acct.getFromaccounts().getId() +" "+acct.getToaccounts().getId());
			 from=accountServ.findbyid(acct.getFromaccounts().getId());
			 to=accountServ.findbyid(acct.getToaccounts().getId());
			 if(from.equals(to)) { 
					throw new PopupException(textConvertionServ.search("same account", langcode));		 
			 }
			if(from == null || to == null) {
				ErrorCodes Err=	errorCodesServ.geterrordesc(1);
				throw new PopupException(textConvertionServ.search(Err.getErrordescription(), langcode));
			}
		} catch (Exception e) {
			// TODO: handle exception
			ErrorCodes Err=	errorCodesServ.geterrordesc(1);
			throw new PopupException(textConvertionServ.search(Err.getErrordescription(), langcode));
		}
		try {
			 amount=new BigDecimal(acct.getAmount());
		} catch (Exception e) {
			// TODO: handle exception
			throw new PopupException(textConvertionServ.search("amount_wrong", langcode));

		}
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "transfer from "+from.getAcctNumber()+" to "+to.getAcctName()+" amount : "+amount, "", 37, 0);

				if(result) {
		     String ref =	accountProcessServ.debit_cash_account("",from.getCurrencyID().getCurrencyISO(), from.getAcctNumber(), amount, acct.getTrxdesc(), langcode);
		        ref =	accountProcessServ.credit_cash_account(ref,from.getCurrencyID().getCurrencyISO(), to.getAcctNumber(), amount, acct.getTrxdesc(), langcode);
			
		        return new Transaction_output(amount, ref);
	
		}else {
		
			throw new PopupException(textConvertionServ.search("not_sucessful", langcode));

			
		}
		
		    
				
	  }
	  
	
	@RequestMapping(value = "/create/{langcode}", method = RequestMethod.POST)
	  public  @ResponseBody ResponseEntity<Acct_obj> create_account(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody Acct_obj acct) {
	    // This returns a JSON or XML with the users
		  
		boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "account in processing", "", 33, 0);
		
		if(result) {
			
			return new ResponseEntity<Acct_obj>(accountProcessServ.create_acct(acct), HttpStatus.OK);
	
		}else {
		
			return new ResponseEntity<Acct_obj>(new Acct_obj(), HttpStatus.NOT_ACCEPTABLE);

			
		}
		
		    
				
	  }
	  
	  

		@RequestMapping(value = "/all/{langcode}", method = RequestMethod.GET)
		  public  ResponseEntity<List<Account>> getAllAccounts(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode){
			boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "Account in processing", "", 36, 0);
			if(result) {
				
				return new ResponseEntity<List<Account>>(accountServ.getall(langcode), HttpStatus.OK);

			}else {
				
				throw new PopupException(textConvertionServ.search("no_data", langcode));

			}
		  }
		
		@RequestMapping(value = "/currency/{langcode}", method = RequestMethod.POST)
		  public  ResponseEntity<textobject> getcurrencybyname(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody String input){
			boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "get currency in processing", "", 36, 0);
			if(result) {
				Account acct;
				try {
					 acct=accountServ.findbyid(Integer.parseInt(input));
				} catch (Exception e) {
					// TODO: handle exception
					ErrorCodes Err=	errorCodesServ.geterrordesc(1);
					throw new PopupException(textConvertionServ.search(Err.getErrordescription(), langcode));
			
				}

				textobject t=new textobject();
				t.setText(acct.getCurrencyID().getCurrencyISO() + " current balance : "+acct.getCurrbalance()+" AVl balance : "+acct.getAvalbalance());
				return new ResponseEntity<textobject>(t, HttpStatus.OK);

			}else {
				
				throw new PopupException(textConvertionServ.search("no_data", langcode));

			}
		
		  }
	
		
		
		@RequestMapping(value = "/key/{langcode}", method = RequestMethod.POST)
		  public  ResponseEntity<List<Account>> getAccountsbycustomercode(HttpServletRequest request,@RequestHeader("Devicecode") String  Devicecode,@RequestHeader("username") String  username,@RequestHeader("usertokean") String  usertokean,@RequestHeader("pageid") String  pagenum,@PathVariable("langcode") String langcode,@RequestBody String input){
			boolean result=  externalServ.Log(request, Devicecode, username, usertokean, pagenum, langcode, "get Account bu customer number in processing", "", 36, 0);
			if(result) {
				return new ResponseEntity<List<Account>>(accountServ.getaccountsbycustomerno(input,langcode), HttpStatus.OK);

			}else {
				
				throw new PopupException(textConvertionServ.search("no_data", langcode));

			}
		
		  }
	
	
	
	
	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	  public @ResponseBody ResponseEntity<Account> saveorupdate(@RequestBody Account input) {
	    // This returns a JSON or XML with the users
		
		Account ouput = null;
		
		if(input.getId() == null || input.getId() == 0) {
			System.out.println("insert");
			try {
			///	 ouput= accountServ.Save(input);
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<Account>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null || ouput.getId() == -1) {
						
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<Account>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			}
			
			return new ResponseEntity<Account>(ouput, HttpStatus.OK);

		}else {
			System.out.println("update "+input.getId());
			
			try {
			//	 ouput= accountServ.update(input, input.getId());
				 if(ouput == null || ouput.getId() == -1) {
					
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<Account>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			} catch (Exception e) {
				// TODO: handle exception
				 if(ouput == null|| ouput.getId() == -1) {
					 
					 ouput.setError(ouput.getError());
					 return new ResponseEntity<Account>(ouput, HttpStatus.BAD_REQUEST);	 
				 }
			}
			
			return new ResponseEntity<Account>(ouput, HttpStatus.OK);
			
		}
		  }
	
	

	
	
	

}
