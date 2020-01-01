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
	
public List<TransactionType> getallstatus(String status) {
		
		return (List<TransactionType>) transactionTypeRepo.findAllstatus( status);
		
}


public boolean checkpaymnet_not(String trx_type) {
	List<TransactionType> cr_dr= (List<TransactionType>) transactionTypeRepo.findAllpaymnet(1);
	boolean paymnet=false;
	for (int i = 0; i < cr_dr.size(); i++) {
		
		if(cr_dr.get(i).getTRXtype().equals(trx_type)) {
			paymnet = true;
			break;			
		}
		
	}
	return paymnet;
}


public List<TransactionType> getallpaymnet(int pay) {
	
	return (List<TransactionType>) transactionTypeRepo.findAllpaymnet(pay);
	
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
		TransactionType ouput1 =transactionTypeRepo.save(ouput);	
		return ouput1;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new TransactionType(-1,e.getMessage());
	}	
	}
	
}


}
