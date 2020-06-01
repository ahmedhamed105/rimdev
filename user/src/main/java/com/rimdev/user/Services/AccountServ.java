package com.rimdev.user.Services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rimdev.user.Exception.PopupException;
import com.rimdev.user.Utils.Generate;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.ouputobject.Acct_obj;

@Service
public class AccountServ {
	
	
	@Autowired
	ConfigurationServ configurationServ;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	@Autowired
	LogServ logServ;
	
	
	
	public String create_acct(HttpServletRequest request,DevicePage devpag,String customer_no,String username,String langcode) {
		

		

try {
	
	Generate gen=new Generate();
	String newtokean=gen.token(30);
	configurationServ.setvalue("account_tokean", newtokean);
	
	Acct_obj acct= new Acct_obj();
	acct.setCustomerno(customer_no);
	acct.setCustomername(username);
	acct.setCurrency(configurationServ.getbykey("default_currency").getConfigvalue());
	RestTemplate restTemplate = new RestTemplate();
    final HttpHeaders headers = new HttpHeaders();
    headers.set("projectcode", configurationServ.getbykey("account_tokean").getConfigvalue());

    //Create a new HttpEntity
     HttpEntity<Acct_obj> entity = new HttpEntity<Acct_obj>(acct,headers);
	
	
	ResponseEntity<Acct_obj> okl = restTemplate.postForEntity("http://localhost:8080/Account/create",entity , Acct_obj.class);

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
	
	String text= "account for customer "+username+"  not created by "+devpag.getUserloginID().getUsername();
	logServ.errorlog(devpag.getDeviceId().getDeviceip(),request,text, devpag.getDeviceId(), devpag.getUserloginID().getId(),  33, langcode,e.getMessage());		

	
	throw new PopupException(textConvertionServ.search("account not created", langcode));	
}

		
	}

}
