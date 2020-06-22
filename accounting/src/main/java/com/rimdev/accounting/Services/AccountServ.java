package com.rimdev.accounting.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Exception.PopupException;
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


@Autowired
TextConvertionServ textConvertionServ;


public Account findbyid(int id) {
	
	
	
	try {
		Optional<Account> flowid =accountRepo.findbyidstatus(id);
		 
		 if (flowid.isPresent()){
			 Account  ouput = flowid.get();
		
			  return ouput;
					}
			else{
			   // alternative processing....
				return null;
			}
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	
}


public List<Account> getall(String langcode) {
	try {
		return (List<Account>) accountRepo.findAll();
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
	} catch (RecoverableDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
	}catch (ScriptException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
	}catch (NonTransientDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
	}
	
}


public List<Account> getaccountsbycustomerno(String cusnumber,String langcode) {
	try {
		return (List<Account>) accountRepo.findbycustomer(cusnumber);
	} catch (TransientDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
	} catch (RecoverableDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
	}catch (ScriptException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
	}catch (NonTransientDataAccessException  se) {
		se.printStackTrace();
		throw new PopupException(textConvertionServ.search("E104", langcode));
	}
	
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
				return null;
			}
	} catch (Exception e) {
		// TODO: handle exception
		return null;
	}
	
}








}
