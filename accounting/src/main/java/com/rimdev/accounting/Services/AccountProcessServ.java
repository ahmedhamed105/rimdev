package com.rimdev.accounting.Services;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Repo.AccountProcessRepo;
import com.rimdev.accounting.inputobject.Acct_obj;

@Service
public class AccountProcessServ {
	
	
	@Autowired 
	private AccountProcessRepo accountProcessRepo;
	
	
	
	public Acct_obj create_acct(Acct_obj input){
		String error="0";
		System.out.println(input.getCustomername()+' '+ input.getCustomerno()+' '+  input.getCurrency());
		error= accountProcessRepo.createaccount(input.getCustomerno(), input.getCustomername(), input.getCurrency());

			System.out.println(error);
			if(error.split(",").length>0)
			{
			input.setError_code(Integer.parseInt(error.split(",")[0]));
			input.setAcctno(error.split(",")[1]);	
				
			}else {
				input.setError_code(Integer.parseInt(error));	
			}
		
		
		return input;
	}
	
	
	
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
