package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.PasswordHistoryRepo;

@Service
public class PasswordHistoryServ {
	
	@Autowired 
	private PasswordHistoryRepo passwordHistoryRepo;

}
