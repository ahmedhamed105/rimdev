package com.rimdev.accounting.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.TransactionType;
import com.rimdev.accounting.Repo.TransactionTypeRepo;
import com.rimdev.accounting.Utils.ObjectUtils;

@Service
public class TransactionTypeServ {
	
	@Autowired 
	private TransactionTypeRepo transactionTypeRepo;
	
	
	
public List<TransactionType> getall() {
		
		return (List<TransactionType>) transactionTypeRepo.findAll();
		
	}
	
	
public TransactionType Save(TransactionType input) {
	
	try {	
		TransactionType ouput =transactionTypeRepo.save(input);	
		return ouput;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new TransactionType(-1,e.getMessage());
	}			
		
	}


public TransactionType update(TransactionType input,Integer id)  {
	TransactionType ouput = null;
	
	try {
		Optional<TransactionType> flowid =transactionTypeRepo.findById(id);
		 
		 if (flowid.isPresent()){
			  ouput = flowid.get();
			// System.out.println(ouput.getCurrencyISO());
			  BeanUtils.copyProperties(input, ouput, ObjectUtils.getNullPropertyNames(input));
			//  System.out.println(ouput.getCurrencyISO());
			   // processing with foo ...
			}
			else{
			   // alternative processing....
				return new TransactionType(-1,"not found");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new TransactionType(-1,e.getMessage());
	}
	
	if(ouput == null) {
		return new TransactionType(-1,"ouput is null");
	}else {
	
	try {	
		TransactionType ouput1 =transactionTypeRepo.save(ouput);	
		return ouput1;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new TransactionType(-1,e.getMessage());
	}	
	}
	
}


}
