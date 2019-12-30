package com.rimdev.accounting.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Repo.CurrencyRepo;
import com.rimdev.accounting.Utils.ObjectUtils;

@Service
public class CurrencyServ {
	
	@Autowired 
	private CurrencyRepo currencyRepo;
	
	
	public List<Currency> getall() {
		
		return (List<Currency>) currencyRepo.findAll();
		
	}
	
	
public Currency Save(Currency input) {
	
	try {	
		Currency ouput =currencyRepo.save(input);	
		return ouput;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new Currency(-1,e.getMessage());
	}			
		
	}


public Currency update(Currency input,Integer id)  {
	Currency ouput = null;
	
	try {
		Optional<Currency> curid =currencyRepo.finbyidCurrency(id);
		 
		 if (curid.isPresent()){
			  ouput = curid.get();
			// System.out.println(ouput.getCurrencyISO());
			  BeanUtils.copyProperties(input, ouput, ObjectUtils.getNullPropertyNames(input));
			//  System.out.println(ouput.getCurrencyISO());
			   // processing with foo ...
			}
			else{
			   // alternative processing....
				return new Currency(-1,"Currency may be not Active");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new Currency(-1,e.getMessage());
	}
	
	if(ouput == null) {
		return new Currency(-1,"ouput is null");
	}else {
	
	try {	
		Currency ouput1 =currencyRepo.save(ouput);	
		return ouput1;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new Currency(-1,e.getMessage());
	}	
	}
	
}




}
