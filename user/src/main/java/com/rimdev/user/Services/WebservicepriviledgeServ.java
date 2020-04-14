package com.rimdev.user.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Exception.NoDataException;
import com.rimdev.user.Repo.WebservicePriviledgeRepo;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.GroupWeb;


@Service
public class WebservicepriviledgeServ {

	
	@Autowired
	 WebservicePriviledgeRepo  webservicepriviledgeRepo;
	
	
	public boolean findwebservices(DevicePage page,String webservice,List<GroupWeb> oblist){
		
		boolean found =false;
		 for (GroupWeb groupWeb : oblist) {
			 
			 System.out.println(groupWeb.getWebservicepriviledgeID().getWebService());
			  if(groupWeb.getWebservicepriviledgeID().getWebService().equals(webservice)) {
			
			
					 if((groupWeb.getWebservicepriviledgeID().getAdminDevice() == 1 && page.getDeviceId().getLogintypeID().getLtype().equals("Admin"))
						|| (groupWeb.getWebservicepriviledgeID().getWebDevice() == 1 && page.getDeviceId().getLogintypeID().getLtype().equals("Web"))
						|| (groupWeb.getWebservicepriviledgeID().getMobileDevice() == 1 && page.getDeviceId().getLogintypeID().getLtype().equals("Mobile"))
							 
							 ) {
						 if(page.getDeviceId().isDesktopDevice() && groupWeb.getWebservicepriviledgeID().getIsdesktop() == 1) {
							 found= true;  
							 
						 }else if(page.getDeviceId().isMobile() && groupWeb.getWebservicepriviledgeID().getIsmobile() == 1) {
							 found= true;   
							 
						 }else if(page.getDeviceId().isTablet() && groupWeb.getWebservicepriviledgeID().getIstablet() == 1) {
							 
							 found= true;  
						 }else {
							 found= false;   
							 
						 }
						 
						 
						
						 
					 } else {
						 found= false;   
						 
					 }
					 
					 
					
				  
				  
				 
				  
			  }  
			
		   }
		 
		 return found;
	}
	
	
	
	public  String makeUrl(HttpServletRequest request,List<String> paramter,List<String> values)
	{
		String URL=request.getRequestURI().toString();
		
		for (String val : values) {	
			URL = URL.replace(val,"{"+ paramter.get(values.indexOf(val))+"}");
		}

	    return URL;
	}

	
	
	
}