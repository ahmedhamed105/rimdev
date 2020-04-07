package com.rimdev.user.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.user.Repo.PagesPriviledgeRepo;
import com.rimdev.user.entities.DevicePage;
import com.rimdev.user.entities.GroupPages;
import com.rimdev.user.entities.GroupParent;
import com.rimdev.user.entities.Pages;

@Service
public class PagesPriviledgeServ {
	
	@Autowired
	PagesPriviledgeRepo pagesPriviledgeRepo;
	
	
	public boolean validpage(Pages page,List<GroupPages> parents,DevicePage devpage) {
		
		boolean found=false;
		 for (GroupPages groupPages : parents) {
			 
			 
			 if(groupPages.getPagespriviledgeID().getPagesID().equals(page)) {
				 
				 if((groupPages.getPagespriviledgeID().getAdminDevice() == 1 && devpage.getDeviceId().getLogintypeID().getLtype().equals("Admin"))
							|| (groupPages.getPagespriviledgeID().getWebDevice() == 1 && devpage.getDeviceId().getLogintypeID().getLtype().equals("Web"))
							|| (groupPages.getPagespriviledgeID().getMobileDevice() == 1 && devpage.getDeviceId().getLogintypeID().getLtype().equals("Mobile"))
								 
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
