package com.rimdev.RES.Service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.RES.Repo.DevicetypeRepo;
import com.rimdev.RES.Repo.OperaSystemRepo;
import com.rimdev.RES.entites.Device_type;
import com.rimdev.RES.entites.OperaSystem;

@Service
public class DevicetypeServ {
	
	
	@Autowired
	DevicetypeRepo devicetypeRepo;
	
	
	
	public List<Device_type> selectall() {
		
		return (List<Device_type>) devicetypeRepo.findAll();
		
	}
	
	
	
public boolean addorupdate(List<Device_type> Devicetype) {
	
	
	if (Devicetype == null || Devicetype.size() == 0) return false;
	
	for (Device_type dtype : Devicetype) {	
		
		if (dtype.getId() == 0) {
			System.out.println("insert "+dtype);
			try {
				devicetypeRepo.save(dtype);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
			
			
		}else {
			
			System.out.println("update "+dtype);
			
			try {
				Device_type udtype= devicetypeRepo.findById((long) dtype.getId()).get();
				BeanUtils.copyProperties(dtype,udtype);
				devicetypeRepo.save(udtype);
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
