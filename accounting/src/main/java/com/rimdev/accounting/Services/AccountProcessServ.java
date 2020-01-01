package com.rimdev.accounting.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Repo.AccountProcessRepo;

@Service
public class AccountProcessServ {
	
	
	@Autowired 
	private AccountProcessRepo accountProcessRepo;
	
	
	public int call_post(String acct_no, String Customer_id){
		int error=0;
		error= accountProcessRepo.main3(acct_no, Customer_id);
		return error;
	}

}
