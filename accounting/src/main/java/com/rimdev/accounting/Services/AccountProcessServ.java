package com.rimdev.accounting.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Repo.AccountProcessRepo;

@Service
public class AccountProcessServ {
	
	
	@Autowired 
	private AccountProcessRepo accountProcessRepo;

}
