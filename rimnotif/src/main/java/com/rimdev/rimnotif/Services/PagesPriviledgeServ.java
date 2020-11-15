package com.rimdev.rimnotif.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimnotif.Repo.PagesPriviledgeRepo;
import com.rimdev.rimnotif.entities.DevicePage;
import com.rimdev.rimnotif.entities.GroupPages;
import com.rimdev.rimnotif.entities.GroupParent;
import com.rimdev.rimnotif.entities.Pages;

@Service
public class PagesPriviledgeServ {
	
	@Autowired
	PagesPriviledgeRepo pagesPriviledgeRepo;
	
	
	public boolean validpage(Pages page,List<GroupPages> parents,DevicePage devpage) {
		
		boolean found=false;
		 for (GroupPages groupPages : parents) {
			 
			 
			 if(groupPages.getPagespriviledgeID().getPagesID().equals(page)) {
				 
				 if((groupPages.getPagespriviledgeID().getAdminDevice() == 1 && devpage.getDeviceId().getApplicationID().getAppname().equals("Admin"))
							|| (groupPages.getPagespriviledgeID().getWebDevice() == 1 && devpage.getDeviceId().getApplicationID().getAppname().equals("Web"))
							|| (groupPages.getPagespriviledgeID().getMobileDevice() == 1 && devpage.getDeviceId().getApplicationID().getAppname().equals("Mobile"))
								 
								 ) {
							 if(devpage.getDeviceId().isDesktopDevice() && groupPages.getPagespriviledgeID().getIsdesktop() == 1) {
								 found= true;  
								 
							 }else if(devpage.getDeviceId().isMobile() && groupPages.getPagespriviledgeID().getIsmobile() == 1) {
								 found= true;   
								 
							 }else if(devpage.getDeviceId().isTablet() && groupPages.getPagespriviledgeID().getIstablet() == 1) {
								 
								 found= true;  
							 }else {
								 found= false;   
								 
							 }
			 }
			 
		 }
		
	}
	
		 return found;
	}

}
