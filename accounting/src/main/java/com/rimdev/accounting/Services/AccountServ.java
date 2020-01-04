package com.rimdev.accounting.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Repo.AccountRepo;
import com.rimdev.accounting.Repo.CurrencyRepo;
import com.rimdev.accounting.Utils.ObjectUtils;



@Service
public class AccountServ {
	
@Autowired 
private AccountRepo accountRepo;

@Autowired 
private CurrencyRepo currencyRepo;


public Account findbyid(int id) {
	
	
	
	try {
		Optional<Account> flowid =accountRepo.findbyidstatus(id);
		 
		 if (flowid.isPresent()){
			 Account  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return new Account(-1,"account or currency not Active");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new Account(-1,e.getMessage());
	}
	
}


public List<Account> getall() {
	
	return (List<Account>) accountRepo.findAll();
	
}

public List<Account> getallstatus(String status) {
	
	return (List<Account>) accountRepo.findAllstatus(status);
	
}

public Account getbyaccount(String acct_no,int currency) {
	
	try {
		Optional<Account> acct =accountRepo.findbyaccount(acct_no, currency);
		 
		 if (acct.isPresent()){
			 Account ouput = acct.get();
			 return ouput;
			}
			else{
			   // alternative processing....
				return new Account(-1,"Account not found");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new Account(-1,e.getMessage());
	}
	
}


public Account getbyRim(String rim_no,int currency) {
	
	try {
		Optional<Account> acct =accountRepo.findbyrim(rim_no, currency);
		 
		 if (acct.isPresent()){
			 Account ouput = acct.get();
			 return ouput;
			}
			else{
			   // alternative processing....
				return new Account(-1,"Rim not have account for this Currency");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new Account(-1,e.getMessage());
	}
	
}

public Account Save(Account input) {
	
	if(input.getCurrencyID() != null) {
		
		try {
			Optional<Currency> flowid =currencyRepo.findById(input.getCurrencyID().getId());
			 
			 if (flowid.isPresent()){
				 Currency curouput = flowid.get();
				 
				}
				else{
				   // alternative processing....
					return new Account(-1,"Currency not Active");
				}
		} catch (Exception e) {
			// TODO: handle exception
			return new Account(-1,e.getMessage());
		}
	}
	
	try {	
		
		Date date = new Date();
		input.setCreatedate(date);
		input.setLastmodification(date);
		input.setEffectiveDate(date);
		Account ouput =accountRepo.save(input);	
		return ouput;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new Account(-1,e.getMessage());
	}			
		
	}


public Account update(Account input,Integer id)  {
	Account ouput = null;
	
	try {
		Optional<Account> flowid =accountRepo.findbyidstatus(id);
		 
		 if (flowid.isPresent()){
			  ouput = flowid.get();
			// System.out.println(ouput.getCurrencyISO());
			  BeanUtils.copyProperties(input, ouput, ObjectUtils.getNullPropertyNames(input));
			//  System.out.println(ouput.getCurrencyISO());
			   // processing with foo ...
			}
			else{
			   // alternative processing....
				return new Account(-1,"account or currency not Active");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new Account(-1,e.getMessage());
	}
	
	if(ouput == null) {
		return new Account(-1,"ouput is null");
	}else {
	
	try {	
		Date date = new Date();
		ouput.setLastmodification(date);
		ouput.setEffectiveDate(date);
		Account ouput1 =accountRepo.save(ouput);	
		return ouput1;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new Account(-1,e.getMessage());
	}	
	}
	
}





}
