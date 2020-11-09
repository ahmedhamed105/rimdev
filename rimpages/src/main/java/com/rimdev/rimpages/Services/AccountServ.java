package com.rimdev.rimpages.Services;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rimdev.rimpages.Exception.PopupException;
import com.rimdev.rimpages.Utils.Generate;
import com.rimdev.rimpages.entities.DevicePage;
import com.rimdev.rimpages.outputobject.Acct_obj;

@Service
public class AccountServ {
	
	
	@Autowired
	ConfigurationServ configurationServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	LogServ logServ;
	
	
	
	public String create_acct(String  reqDevicecode,String  requsername,String  requsertokean,String reqpagenum,HttpServletRequest request,DevicePage devpag,String customer_no,String username,String langcode) {

try {
	 ObjectMapper mapper = new ObjectMapper();
	Acct_obj acct= new Acct_obj();
	acct.setCustomerno(customer_no);
	acct.setCustomername(username);
	acct.setCurrency(configurationServ.getbykey("default_currency").getConfigvalue());
	RestTemplate restTemplate = new RestTemplate();
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
 // set `accept` header
 headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
 
    headers.set("Devicecode", reqDevicecode);
    headers.set("username", requsername);
    headers.set("usertokean", requsertokean);
    headers.set("pageid", reqpagenum);
     

    //Create a new HttpEntity
     HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(acct),headers);
	
	
	ResponseEntity<Acct_obj> okl = restTemplate.postForEntity("http://localhost:8080/Account/create/"+langcode,entity, Acct_obj.class);
System.out.println(okl.getBody().getError_code());
	if(okl.getBody().getError_code() == 1) {
		
		String text= "account "+okl.getBody().getAcctno()+" created by "+devpag.getUserloginID().getUsername();
		logServ.info(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  33, langcode," ");		
    	
		
		return okl.getBody().getAcctno();
	}else {
		String text= "account for customer "+username+"  not created by "+devpag.getUserloginID().getUsername();
		logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  33, langcode,"");		
   
		
	throw new PopupException(textConvertionServ.search("account not created", langcode));	
	}	
} catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
	String text= "account for customer "+username+"  not created by "+devpag.getUserloginID().getUsername();
	logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  33, langcode,e.getMessage());		

	
	throw new PopupException(textConvertionServ.search("account not created", langcode));	
}

		
	}

}
