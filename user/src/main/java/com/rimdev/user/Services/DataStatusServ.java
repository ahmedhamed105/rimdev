package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.DataStatusRepo;
import com.rimdev.user.entities.DataStatus;

@Service
public class DataStatusServ {
	
	@Autowired
	DataStatusRepo dataStatusRepo;
	
	
public List<DataStatus> getall() {
		
		return (List<DataStatus>) dataStatusRepo.findAll();
		
	}

}
