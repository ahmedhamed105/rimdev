package com.rimdev.rimlog.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.rimlog.Repo.ParentPriviledgeRepo;
import com.rimdev.rimlog.entities.DevicePage;
import com.rimdev.rimlog.entities.GroupParent;
import com.rimdev.rimlog.outputobject.parent_comp;

@Service
public class ParentPriviledgeServ {
	
	@Autowired
	ParentPriviledgeRepo  parentPriviledgeRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	
public List<parent_comp> removeparentnoprivil(List<parent_comp> comp,List<GroupParent> parents,DevicePage devpage){
		
	List<parent_comp> outcomp =new ArrayList<parent_comp>();
		 for (GroupParent groupParent : parents) {
			 
			 for (parent_comp componen : comp) {
				// System.out.println(groupParent.getParentpriviledgeID().getParentcomponentID()); 
				  if(groupParent.getParentpriviledgeID().getParentcomponentID().equals(componen.getParent())) {
					  if((groupParent.getParentpriviledgeID().getAdminDevice() == 1 && devpage.getDeviceId().getApplicationID().getAppname().equals("Admin"))
								|| (groupParent.getParentpriviledgeID().getWebDevice() == 1 && devpage.getDeviceId().getApplicationID().getAppname().equals("Web"))
								|| (groupParent.getParentpriviledgeID().getMobileDevice() == 1 && devpage.getDeviceId().getApplicationID().getAppname().equals("Mobile"))
									 
									 ) {
								 if(devpage.getDeviceId().isDesktopDevice() && groupParent.getParentpriviledgeID().getIsdesktop() == 1) {
									 outcomp.add(componen);
									 
								 }else if(devpage.getDeviceId().isMobile() && groupParent.getParentpriviledgeID().getIsmobile() == 1) {
									 outcomp.add(componen);  
									 
								 }else if(devpage.getDeviceId().isTablet() && groupParent.getParentpriviledgeID().getIstablet() == 1) {
									 
									 outcomp.add(componen);
								 }else {
									
									 
								 }
					  }
					 
				  }
			 } 
			 
		 }
			
		 
		 return outcomp;
	}
	

	
	
}
