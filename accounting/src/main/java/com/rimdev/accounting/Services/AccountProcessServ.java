package com.rimdev.accounting.Services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Repo.AccountProcessRepo;

@Service
public class AccountProcessServ {
	
	
	@Autowired 
	private AccountProcessRepo accountProcessRepo;
	
	
	
	public int call_post(String Customer_id,String Currency,String acct_no,BigDecimal amount,String Trx_type,String Trx_flow){
		int error=0;
		error= accountProcessRepo.main3(Customer_id, Currency, acct_no, amount, Trx_type, Trx_flow);
		return error;
	}

}
