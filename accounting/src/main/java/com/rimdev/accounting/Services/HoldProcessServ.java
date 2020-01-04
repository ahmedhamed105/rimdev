package com.rimdev.accounting.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Enttities.HoldProcess;
import com.rimdev.accounting.Repo.HoldProcessRepo;
import com.rimdev.accounting.Utils.ObjectUtils;

@Service
public class HoldProcessServ {

	@Autowired 
	private HoldProcessRepo holdProcessRepo;
	
	
	public List<HoldProcess> getallstatus(String status) {
		
		return (List<HoldProcess>) holdProcessRepo.findAllstatus(status);
		
	}
	
public List<HoldProcess> getalllikedescription(String description) {
		
		return (List<HoldProcess>) holdProcessRepo.findAllLikeDesc(description);
		
	}

public List<HoldProcess> getalllikedescription(String status,String description) {
	
	return (List<HoldProcess>) holdProcessRepo.findAllstatusLikeDesc(status,description);
	
}


public HoldProcess getbyholdid(int holdid) {
	
	try {
		Optional<HoldProcess> curid =holdProcessRepo.findbyholdid(holdid);
		 
		 if (curid.isPresent()){
			 HoldProcess  ouput = curid.get();
			 return ouput;
			 
			}
			else{
			   // alternative processing....
				return new HoldProcess(-1,"not found");
			}
	} catch (Exception e) {
		// TODO: handle exception
		return new HoldProcess(-1,e.getMessage());
	}

}
	
}
