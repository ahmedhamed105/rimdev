package com.rimdev.accounting.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Repo.CurrencyRepo;
import com.rimdev.accounting.Repo.FlowTypeRepo;
import com.rimdev.accounting.Utils.ObjectUtils;

@Service
public class FlowTypeServ {
	
	@Autowired 
	private FlowTypeRepo flowTypeRepo;
	
	
public List<FlowType> getall() {
		
		return (List<FlowType>) flowTypeRepo.findAll();
		
	}
	
	
public FlowType Save(FlowType input) {
	
	try {	
		FlowType ouput =flowTypeRepo.save(input);	
		return ouput;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new FlowType(-1,e.getMessage());
	}			
		
	}


public FlowType update(FlowType input,Integer id)  {
	FlowType ouput = null;
	
	try {
		Optional<FlowType> flowid =flowTypeRepo.findById(id);
		 
		 if (flowid.isPresent()){
			  ouput = flowid.get();
			// System.out.println(ouput.getCurrencyISO());
			  BeanUtils.copyProperties(input, ouput, ObjectUtils.getNullPropertyNames(input));
			//  System.out.println(ouput.getCurrencyISO());
			   // processing with foo ...
			}
			else{
			   // alternative processing....
				return new FlowType(-1,"not found");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new FlowType(-1,e.getMessage());
	}
	
	if(ouput == null) {
		return new FlowType(-1,"ouput is null");
	}else {
	
	try {	
		FlowType ouput1 =flowTypeRepo.save(ouput);	
		return ouput1;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new FlowType(-1,e.getMessage());
	}	
	}
	
}


}
