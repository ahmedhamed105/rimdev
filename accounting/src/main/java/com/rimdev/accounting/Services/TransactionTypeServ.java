package com.rimdev.accounting.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.Currency;
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
	
public List<TransactionType> getallstatus(String status) {
		
		return (List<TransactionType>) transactionTypeRepo.findAllstatus( status);
		
}


public TransactionType checkpaymnet_not(int trx_type) {
	
	
	try {
		
		List<TransactionType> cu=(List<TransactionType>) transactionTypeRepo.findAllstatus("Active");
		
		TransactionType paymnet=null;
		for (int i = 0; i < cu.size(); i++) {
			
			if(cu.get(i).getTrxcode() == trx_type ) {
				paymnet = cu.get(i);
				break;			
			}
			
		}
		if(paymnet == null) {
			return new TransactionType(-1,"transaction not found");
		}else {
		return paymnet;
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		return new TransactionType(-1,e.getMessage());
	}
	
	
	
}


public List<TransactionType> getallpaymnet(int pay) {
	
	return (List<TransactionType>) transactionTypeRepo.findAllpaymnet(pay);
	
}
	
	
public TransactionType Save(TransactionType input) {
	Date date = new Date();
	input.setCreateDate(date);
	input.setEffectiveDate(date);
	
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
				return new TransactionType(-1,"Transaction Type not Active");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new TransactionType(-1,e.getMessage());
	}
	
	if(ouput == null) {
		return new TransactionType(-1,"ouput is null");
	}else {
	
	try {	
		Date date = new Date();
		ouput.setEffectiveDate(date);
		TransactionType ouput1 =transactionTypeRepo.save(ouput);	
		return ouput1;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new TransactionType(-1,e.getMessage());
	}	
	}
	
}


}
