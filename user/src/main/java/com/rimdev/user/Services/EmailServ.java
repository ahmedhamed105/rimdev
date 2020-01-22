package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.EmailRepo;

@Service
public class EmailServ {

	@Autowired
	private EmailRepo emailRepo;

}
