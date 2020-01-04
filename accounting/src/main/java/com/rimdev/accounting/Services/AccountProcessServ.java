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
	
	
	
	public String call_post( String reference_no,
			   String Currency,
			   String acct_no,
			   BigDecimal amount,
			   int Trantypecode,
			   String Trx_flow,
			   String Trx_desc,
			   int hold_id
			){
		String error="0";
		error= accountProcessRepo.main3(reference_no, Currency, acct_no, amount, Trantypecode, Trx_flow, Trx_desc, hold_id);
		return error;
	}
	
	
	public String call_hold( String reference_no,
			   String Currency,
			   String acct_no,
			   BigDecimal amount,
			   int Trantypecode,
			   String Trx_flow,
			   String Trx_desc,
			   int hold_id
			){
		String error="0";
		error= accountProcessRepo.hold3(reference_no, Currency, acct_no, amount, Trantypecode, Trx_flow, Trx_desc, hold_id);
		return error;
	}

}
