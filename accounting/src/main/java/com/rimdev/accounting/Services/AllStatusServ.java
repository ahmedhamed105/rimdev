package com.rimdev.accounting.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.accounting.Enttities.AllStatus;
import com.rimdev.accounting.Repo.AllStatusRepo;

@Service
public class AllStatusServ {
	
	@Autowired
	AllStatusRepo allStatusRepo;
	
public List<AllStatus> getall(String langcode) {
		
		return (List<AllStatus>) allStatusRepo.findAll();
		
	}

}
