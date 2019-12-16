package com.rimdev.RES.Service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rimdev.RES.Repo.DevicetableRepo;
import com.rimdev.RES.entites.Device_table;

@Service
public class devicetableServ {
	
	@Autowired
	DevicetableRepo devicetableRepo;
	
	
	
	public List<Device_table> selectall() {
		
		return (List<Device_table>) devicetableRepo.findAll();
		
	}
	
	
	
public boolean addorupdate(List<Device_table> paramters) {
	
	
	if (paramters == null || paramters.size() == 0) return false;
	
	for (Device_table device_table : paramters) {	
		
		if (device_table.getId() == 0) {
			System.out.println("insert "+device_table);
			try {
				devicetableRepo.save(device_table);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
			
			
		}else {
			
			System.out.println("update "+device_table);
			
			try {
				Device_table updevice_table= devicetableRepo.findById((long) device_table.getId()).get();
				BeanUtils.copyProperties(device_table,updevice_table);
				devicetableRepo.save(updevice_table);
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
