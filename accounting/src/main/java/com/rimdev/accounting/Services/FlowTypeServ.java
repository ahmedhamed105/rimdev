package com.rimdev.accounting.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.FlowType;
import com.rimdev.accounting.Repo.FlowTypeRepo;
import com.rimdev.accounting.Utils.ObjectUtils;

@Service
public class FlowTypeServ {
	
	@Autowired 
	private FlowTypeRepo flowTypeRepo;
	
	
public List<FlowType> getall() {
		
		return (List<FlowType>) flowTypeRepo.findAll();
		
	}

public List<FlowType> getallstatus(String status) {
	
	return (List<FlowType>) flowTypeRepo.findAllstatus( status);
	
}
	
	
public FlowType Save(FlowType input) {
	
	try {	
		
		Date date = new Date();
		input.setCreateDate(date);
		input.setEffectiveDate(date);
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
				return new FlowType(-1,"Flow type may be not Active");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new FlowType(-1,e.getMessage());
	}
	
	if(ouput == null) {
		return new FlowType(-1,"ouput is null");
	}else {
	
	try {	
		Date date = new Date();
		ouput.setEffectiveDate(date);
		FlowType ouput1 =flowTypeRepo.save(ouput);	
		return ouput1;
	} catch (Exception e) {
		// TODO: handle exception
		
		return new FlowType(-1,e.getMessage());
	}	
	}
	
}


}