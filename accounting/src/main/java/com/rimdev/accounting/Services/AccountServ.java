package com.rimdev.accounting.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.Account;
import com.rimdev.accounting.Repo.AccountRepo;



@Service
public class AccountServ {
	
@Autowired 
private AccountRepo accountRepo;


public List<Account> getall() {
	
	return (List<Account>) accountRepo.findAll();
	
}



}
