package com.rimdev.accounting.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Repo.FlowTypeRepo;
import com.rimdev.accounting.Repo.TransactionTypeRepo;

@Service
public class TransactionTypeServ {
	
	@Autowired 
	private TransactionTypeRepo transactionTypeRepo;

}
