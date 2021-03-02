package com.rimdev.gateway.Services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rimdev.gateway.Exception.BlockedException;
import com.rimdev.gateway.Exception.NoResultException;
import com.rimdev.gateway.Exception.NooauthException;
import com.rimdev.gateway.entities.Device;
import com.rimdev.gateway.ouputobject.Deviceob;


@Service
public class DeviceExternalServ {

	@Autowired
	LangExternalServ langExternalServ;
	

	public Device searchdevice(Device device, String langcode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			Deviceob txtgo = new Deviceob();
			Device dev= new Device(device.getId(),device.getDevicecode());
			txtgo.setData(dev);

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			System.out.println(mapper.writeValueAsString(txtgo));

			// Create a new HttpEntity

			HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(txtgo), headers);

			ResponseEntity<Device> okl = restTemplate.postForEntity("http://localhost:8092/Device/search/" + langcode,
					entity, Device.class);

			if (okl.getStatusCodeValue() == 200) {

				return okl.getBody();
			} else {

				throw new NoResultException(langExternalServ.search("E200", langcode));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoResultException(langExternalServ.search("E200", langcode));
		}
	}
	
	
	public Device saveorupdatedevice(Device input, String langcode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			Deviceob txtgo = new Deviceob();
			txtgo.setData(input);

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			System.out.println(mapper.writeValueAsString(txtgo));

			// Create a new HttpEntity

			HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(txtgo), headers);

			ResponseEntity<Device> okl = restTemplate.postForEntity("http://localhost:8092/Device/saveorupdate/" + langcode,
					entity, Device.class);

			if (okl.getStatusCodeValue() == 200) {

				return okl.getBody();
			} else {

				throw new NoResultException(langExternalServ.search("E200", langcode));

			}

		} catch (MethodNotAllowed e) {
			// TODO Auto-generated catch block
			
			throw new BlockedException(langExternalServ.search("E201", langcode));
		}catch (Unauthorized e) {
			// TODO: handle exception
			throw new NooauthException(langExternalServ.search("E200", langcode));
		}catch (Exception e) {
			// TODO: handle exception
			throw new NooauthException(langExternalServ.search("E200", langcode));
		}
	}


}
