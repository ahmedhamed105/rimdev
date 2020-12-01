package com.rimdev.rimdevices.Services;

import java.io.File;
import java.net.InetAddress;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.rimdev.rimdevices.Exception.NoResultException;
import com.rimdev.rimdevices.Repo.DeviceipRepo;
import com.rimdev.rimdevices.entities.Device;
import com.rimdev.rimdevices.entities.Deviceip;

@Service
public class DeviceipServ {
	
	@Autowired
	DeviceipRepo deviceipRepo;
	
	@Autowired
	LangExternalServ textConvertionServ;
	
	
	

public Deviceip getip(String ip) {
	
	Deviceip a= new Deviceip();	
	a.setIpAddress(ip);
	
	try {
		File database  = new File(
				getClass().getClassLoader().getResource("GeoLite2-City.mmdb").getFile()
			);


			DatabaseReader dbReader = new DatabaseReader.Builder(database).build();


		InetAddress ipAddress = InetAddress.getByName(ip);
		CityResponse response = dbReader.city(ipAddress);
		     
		String countryName = response.getCountry().getName();
		a.setCountry(countryName);
		String cityName = response.getCity().getName();
		a.setCity(cityName);
		String state = response.getLeastSpecificSubdivision().getName();
		a.setState(state);

		String latitude = 
		response.getLocation().getLatitude().toString();

		a.setLatitude(latitude);

		String longitude = 
		response.getLocation().getLongitude().toString();
		a.setLongitude(longitude);

		String timezone = 
		response.getLocation().getTimeZone().toString();
		a.setTimezone(timezone);


		String subneting = 
		response.getTraits().getNetwork().toString();
		a.setSubneting(subneting);

		
	} catch (Exception e) {
		// TODO: handle exception
		
	
	}
	

		return a;
	
	}
	
	
	public void savebyip(Device input,String langcode){
		
		Deviceip geo=getip(input.getDeviceip());
		geo.setDeviceId(input);
		
			Deviceip out = getDeviceip(geo.getIpAddress(),geo.getDeviceId().getId(),langcode);
			
			if(out == null)
			{
		     
				saveip(geo, langcode);
				
			}else {
				
				updateip(out, langcode);
				
			}
	
		
		
		
	}
	
	
	
	

public Deviceip getDeviceip(String ip,int deviceid,String langcode) {
	
	try {
		Optional<Deviceip> flowid =deviceipRepo.findbyip(ip,deviceid);
		 
		 if (flowid.isPresent()){
			 return flowid.get();
		

					}
			else{
			   // alternative processing....
				
				return null;
				
			}
	}  catch (TransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E203", langcode));
    } catch (RecoverableDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E203", langcode));
    }catch (ScriptException  se) {
		throw new NoResultException(textConvertionServ.search("E203", langcode));
    }catch (NonTransientDataAccessException  se) {
		throw new NoResultException(textConvertionServ.search("E203", langcode));
    }

	
}




public void updateip(Deviceip input,String langcode) {
	
	//	System.out.println(input.getIpAddress());
	    Date date = new Date();
		input.setDeviceipmodify(date);		
		
		try {
			deviceipRepo.save(input);	
		} catch (TransientDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E203", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E203", langcode));
	    }catch (ScriptException  se) {
			throw new NoResultException(textConvertionServ.search("E203", langcode));
	    }catch (NonTransientDataAccessException  se) {
	    	se.printStackTrace();
			throw new NoResultException(textConvertionServ.search("E203", langcode));
	    }
		

	
}


public void saveip(Deviceip input,String langcode) {
	
	//	System.out.println(input.getIpAddress());
	    Date date = new Date();
		input.setDeviceipcreate(date);
		input.setDeviceipmodify(date);		
		
		try {
			deviceipRepo.save(input);	
		} catch (TransientDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E203", langcode));
	    } catch (RecoverableDataAccessException  se) {
			throw new NoResultException(textConvertionServ.search("E203", langcode));
	    }catch (ScriptException  se) {
			throw new NoResultException(textConvertionServ.search("E203", langcode));
	    }catch (NonTransientDataAccessException  se) {
	    	se.printStackTrace();
			throw new NoResultException(textConvertionServ.search("E203", langcode));
	    }
		

	
}




}
