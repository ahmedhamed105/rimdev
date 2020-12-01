package com.rimdev.rimauth.Services;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rimdev.rimauth.Exception.NoResultException;
import com.rimdev.rimauth.entities.Device;
import com.rimdev.rimauth.entities.Pages;
import com.rimdev.rimauth.entities.User;
import com.rimdev.rimauth.outputobject.Deviceob;
import com.rimdev.rimauth.outputobject.Userob;
import com.rimdev.rimauth.outputobject.langob;
import com.rimdev.rimauth.outputobject.pagesob;

@Service
public class ExternalServ {

	public String search(String txt, String langcode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			langob txtgo = new langob();
			txtgo.setLangcode(langcode);
			txtgo.setLangtittle(txt);

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			System.out.println(mapper.writeValueAsString(txtgo));

			// Create a new HttpEntity

			HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(txtgo), headers);

			ResponseEntity<langob> okl = restTemplate.postForEntity("http://localhost:8091/Lang/translate/" + langcode,
					entity, langob.class);

			if (okl.getStatusCodeValue() == 200) {

				return okl.getBody().getLangtittle();
			} else {

				return okl.getBody().getLangtittle();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return txt;
		}
	}

	public Device getdevicebyid(int deviceid, String langcode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			Deviceob txtgo = new Deviceob();
			txtgo.setDeviceid(deviceid);

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			System.out.println(mapper.writeValueAsString(txtgo));

			// Create a new HttpEntity

			HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(txtgo), headers);

			ResponseEntity<Device> okl = restTemplate.postForEntity("http://localhost:8092/Device/getbyid/" + langcode,
					entity, Device.class);

			if (okl.getStatusCodeValue() == 200) {

				return okl.getBody();
			} else {

				throw new NoResultException(search("E200", langcode));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoResultException(search("E200", langcode));
		}
	}

	public Pages getpagebyid(int pageid, String langcode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			pagesob txtgo = new pagesob();
			txtgo.setPageid(pageid);

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			System.out.println(mapper.writeValueAsString(txtgo));

			// Create a new HttpEntity

			HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(txtgo), headers);

			ResponseEntity<Pages> okl = restTemplate.postForEntity("http://localhost:8093/Page/getbyid/" + langcode,
					entity, Pages.class);

			if (okl.getStatusCodeValue() == 200) {

				return okl.getBody();
			} else {

				throw new NoResultException(search("E400", langcode));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoResultException(search("E400", langcode));
		}
	}

	public User getuserbyid(int userid, String langcode) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			Userob txtgo = new Userob();
			txtgo.setUserid(userid);

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			// set `accept` header
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			System.out.println(mapper.writeValueAsString(txtgo));

			// Create a new HttpEntity

			HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(txtgo), headers);

			ResponseEntity<User> okl = restTemplate.postForEntity("http://localhost:8093/User/getbyid/" + langcode,
					entity, User.class);

			if (okl.getStatusCodeValue() == 200) {

				return okl.getBody();
			} else {

				throw new NoResultException(search("E500", langcode));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoResultException(search("E500", langcode));
		}
	}

}
