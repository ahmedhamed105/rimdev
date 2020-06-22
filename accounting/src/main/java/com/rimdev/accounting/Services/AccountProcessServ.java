package com.rimdev.accounting.Services;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.ErrorCodes;
import com.rimdev.accounting.Exception.PopupException;
import com.rimdev.accounting.Repo.AccountProcessRepo;
import com.rimdev.accounting.inputobject.Acct_obj;
import com.rimdev.accounting.inputobject.transfer_obj;

@Service
public class AccountProcessServ {
	
	
	@Autowired 
	private AccountProcessRepo accountProcessRepo;
	
	@Autowired
	ErrorCodesServ errorCodesServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
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
	
	
	
	public String Fdebit_cash_account(String ref,String Currency,
			   String acct_no,
			   BigDecimal amount,
			   String Trx_desc,
			   String langcode
			){
		String error="0";
		error= accountProcessRepo.main3(ref, Currency, acct_no, amount, 101, "cash", Trx_desc, 0);
		if(error.split(",").length>0)
		{
		if(Integer.parseInt(error.split(",")[0])>0) {
			ErrorCodes Err=	errorCodesServ.geterrordesc(Integer.parseInt(error.split(",")[0]));
			throw new PopupException(textConvertionServ.search(Err.getErrordescription(), langcode));				
		}
		ref=error.split(",")[1];	
			
		}else {
			throw new PopupException(textConvertionServ.search("not_sucessful", langcode));				
		}
	
	
	return ref;
	}
	
	public String debit_cash_account(String ref,String Currency,
			   String acct_no,
			   BigDecimal amount,
			   String Trx_desc,
			   String langcode
			){
		String error="0";
		error= accountProcessRepo.main3(ref, Currency, acct_no, amount, 100, "cash", Trx_desc, 0);
		if(error.split(",").length>0)
		{
		if(Integer.parseInt(error.split(",")[0])>0) {
			ErrorCodes Err=	errorCodesServ.geterrordesc(Integer.parseInt(error.split(",")[0]));
			throw new PopupException(textConvertionServ.search(Err.getErrordescription(), langcode));				
		}
		ref=error.split(",")[1];	
			
		}else {
			throw new PopupException(textConvertionServ.search("not_sucessful", langcode));				
		}
	
	
	return ref;
	}
	
	public String credit_cash_account(String ref,String Currency,
			   String acct_no,
			   BigDecimal amount,
			   String Trx_desc,
			   String langcode
			){
		String error="0";
		error= accountProcessRepo.main3(ref, Currency, acct_no, amount, 200, "cash", Trx_desc, 0);
		if(error.split(",").length>0)
		{
		if(Integer.parseInt(error.split(",")[0])>0) {
			ErrorCodes Err=	errorCodesServ.geterrordesc(Integer.parseInt(error.split(",")[0]));
			throw new PopupException(textConvertionServ.search(Err.getErrordescription(), langcode));				
		}
		ref=error.split(",")[1];	
			
		}else {
			throw new PopupException(textConvertionServ.search("not_sucessful", langcode));				
		}
	
	
	return ref;
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
