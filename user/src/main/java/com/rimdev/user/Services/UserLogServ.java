package com.rimdev.user.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rimdev.user.Repo.UserLogRepo;

@Service
public class UserLogServ {

	@Autowired 
	private UserLogRepo userLogRepo;
}
