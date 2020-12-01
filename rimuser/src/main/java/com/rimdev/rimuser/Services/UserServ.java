package com.rimdev.rimuser.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.rimuser.Exception.PopupException;
import com.rimdev.rimuser.Repo.UserRepo;
import com.rimdev.rimuser.Utils.Generate;
import com.rimdev.rimuser.entities.Component;
import com.rimdev.rimuser.entities.DevicePage;
import com.rimdev.rimuser.entities.FilesUpload;
import com.rimdev.rimuser.entities.User;
import com.rimdev.rimuser.entities.UserFile;
import com.rimdev.rimuser.entities.UserLogin;

@Service
public class UserServ {
	
	
	@Autowired 
	 UserRepo userRepo;
	
	@Autowired
	LangExternalServ textConvertionServ;

	
	@Autowired
	UserLoginServ userLoginServ;
	
	
	

	
	








}
