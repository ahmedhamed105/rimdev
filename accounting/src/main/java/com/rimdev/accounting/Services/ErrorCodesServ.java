package com.rimdev.accounting.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.Currency;
import com.rimdev.accounting.Enttities.ErrorCodes;
import com.rimdev.accounting.Repo.CurrencyRepo;
import com.rimdev.accounting.Repo.ErrorCodesRepo;
import com.rimdev.accounting.Utils.ObjectUtils;

@Service
public class ErrorCodesServ {
	
	@Autowired 
	private ErrorCodesRepo errorCodesRepo;
	
	
	public ErrorCodes geterrordesc(int error_code) {
		
		try {
			Optional<ErrorCodes> curid =errorCodesRepo.findbyiderrorcode(error_code);
			 
			 if (curid.isPresent()){
				 ErrorCodes  ouput = curid.get();
				 return ouput;
					}
				else{
				   // alternative processing....
					return new ErrorCodes(-1,"not found");
				}
		} catch (Exception e) {
			// TODO: handle exception
			return new ErrorCodes(-1,e.getMessage());
		}
		
	
	}

}
