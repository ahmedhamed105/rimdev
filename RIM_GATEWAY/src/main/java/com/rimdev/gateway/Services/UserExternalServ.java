package com.rimdev.gateway.Services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rimdev.gateway.Exception.NoResultException;
import com.rimdev.gateway.entities.User;
import com.rimdev.gateway.entities.UserLogin;
import com.rimdev.gateway.ouputobject.Deviceob;
import com.rimdev.gateway.ouputobject.Userob;


@Service
public class UserExternalServ {
	
	
	@Autowired
	LangExternalServ langExternalServ;

	public User getuserbyid(int userid, String langcode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			Userob txtgo = new Userob();
			txtgo.setUserid(userid);

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			System.out.println(mapper.writeValueAsString(txtgo));

			// Create a new HttpEntity

			HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(txtgo), headers);

			ResponseEntity<User> okl = restTemplate.postForEntity("http://localhost:8094/User/getbyid/" + langcode,
					entity, User.class);

			if (okl.getStatusCodeValue() == 200) {

				return okl.getBody();
			} else {

				throw new NoResultException(langExternalServ.search("E500", langcode));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoResultException(langExternalServ.search("E500", langcode));
		}
	}
	
	
	public UserLogin getuserloginbyusername(String username,String usertokean, String langcode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			Deviceob txtgo = new Deviceob();
			UserLogin userid= new UserLogin(usertokean,username);
			txtgo.setUserid(userid);

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			System.out.println(mapper.writeValueAsString(txtgo));

			// Create a new HttpEntity

			HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(txtgo), headers);

			ResponseEntity<UserLogin> okl = restTemplate.postForEntity("http://localhost:8094/userlogin/getuserlogin/" + langcode,
					entity, UserLogin.class);

			if (okl.getStatusCodeValue() == 200) {

				return okl.getBody();
			} else {

				throw new NoResultException(langExternalServ.search("E500", langcode));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoResultException(langExternalServ.search("E500", langcode));
		}
	}

}
