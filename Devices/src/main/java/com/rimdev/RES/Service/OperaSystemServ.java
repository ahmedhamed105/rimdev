package com.rimdev.RES.Service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.RES.Repo.DevicetableRepo;
import com.rimdev.RES.Repo.OperaSystemRepo;
import com.rimdev.RES.entites.Device_table;
import com.rimdev.RES.entites.OperaSystem;

@Service
public class OperaSystemServ {
	
	
	@Autowired
	OperaSystemRepo operaSystemRepo;
	
	
	
	public List<OperaSystem> selectall() {
		
		return (List<OperaSystem>) operaSystemRepo.findAll();
		
	}
	
	
	
public boolean addorupdate(List<OperaSystem> opersys) {
	
	
	if (opersys == null || opersys.size() == 0) return false;
	
	for (OperaSystem os : opersys) {	
		
		if (os.getId() == 0) {
			System.out.println("insert "+os);
			try {
				operaSystemRepo.save(os);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
			
			
		}else {
			
			System.out.println("update "+os);
			
			try {
				OperaSystem upos= operaSystemRepo.findById((long) os.getId()).get();
				BeanUtils.copyProperties(os,upos);
				operaSystemRepo.save(upos);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
			
		
			
		}
		
	}
	
	return true;
	
		
		
		
	}

}
