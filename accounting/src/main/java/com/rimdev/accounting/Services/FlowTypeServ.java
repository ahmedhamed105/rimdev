package com.rimdev.accounting.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Repo.CurrencyRepo;
import com.rimdev.accounting.Repo.FlowTypeRepo;

@Service
public class FlowTypeServ {
	
	@Autowired 
	private FlowTypeRepo flowTypeRepo;

}
