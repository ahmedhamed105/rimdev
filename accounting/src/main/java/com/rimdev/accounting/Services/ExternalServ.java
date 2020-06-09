package com.rimdev.accounting.Services;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rimdev.accounting.inputobject.authobject;
import com.rimdev.accounting.inputobject.authouput;

@Service
public class ExternalServ {
	
	
	public boolean auth(HttpServletRequest request,String  Devicecode,String  username,String  usertokean,String  pagenum,String langcode) {
		  try {
			  RestTemplate restTemplate = new RestTemplate();
			  ObjectMapper mapper = new ObjectMapper();
			  authobject auth=new authobject();
			  auth.setRequestip(request.getRemoteAddr());
			  auth.setRequestURL(request.getRequestURI().toString());
			  auth.setDevicecode(Devicecode);
			  auth.setLangcode(langcode);
			  auth.setPagenum(pagenum);
			  auth.setUsername(username);
			  auth.setUsertokean(usertokean);
			  auth.setParamter(new ArrayList<>());
			  auth.setValues(new ArrayList<>());  
			  auth.setLogtext("");
			  auth.setInfo(false);
			  auth.setLogtype(0);
			  auth.setLogException("");
			  final HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			 // set `accept` header
			 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			     System.out.println(mapper.writeValueAsString(auth));

			    //Create a new HttpEntity
			    
					HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(auth),headers);
				
				
			  
			  ResponseEntity<authouput> okl = restTemplate.postForEntity("http://localhost:8081/Auth/get/"+langcode,entity, authouput.class);

			
			if(okl.getStatusCodeValue() == 200) {
				
					return true;		
			}else {
			
				return false;
				
			}
			
			     } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
	}
	
	
	public boolean Log(HttpServletRequest request,String  Devicecode,String  username,String  usertokean,String  pagenum,String langcode,String text,String Exception,int logtype,int errortype) {
		  try {
			  RestTemplate restTemplate = new RestTemplate();
			  ObjectMapper mapper = new ObjectMapper();
			  authobject auth=new authobject();
			  auth.setRequestip(request.getRemoteAddr());
			  auth.setRequestURL(request.getRequestURI().toString());
			  auth.setDevicecode(Devicecode);
			  auth.setLangcode(langcode);
			  auth.setPagenum(pagenum);
			  auth.setUsername(username);
			  auth.setUsertokean(usertokean);
			  auth.setParamter(new ArrayList<>());
			  auth.setValues(new ArrayList<>());  
			  auth.setLogtext(text);
			  if(errortype == 0) {
				  auth.setInfo(true);  
			  }else if(errortype == 1) {
				  auth.setErorr(true); 
				  
			  }else if(errortype == 2) {
				  auth.setWarning(true); 
				  
			  }else if(errortype == 3) {
				  auth.setFatal(true); 
				  
			  }else {
				  auth.setOther(true); 
				  
			  }
			  
			  auth.setLogtype(logtype);
			  auth.setLogException(Exception);
			  final HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_JSON);
			 // set `accept` header
			 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			     System.out.println(mapper.writeValueAsString(auth));

			    //Create a new HttpEntity
			    
					HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(auth),headers);
				
				
			  
			  ResponseEntity<authouput> okl = restTemplate.postForEntity("http://localhost:8081/Auth/get/"+langcode,entity, authouput.class);

			
			if(okl.getStatusCodeValue() == 200) {
				
					return true;		
			}else {
			
				return false;
				
			}
			
			     } catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
	}

}
