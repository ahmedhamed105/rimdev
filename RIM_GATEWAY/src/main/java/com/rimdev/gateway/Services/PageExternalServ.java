package com.rimdev.gateway.Services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rimdev.gateway.Exception.NoResultException;
import com.rimdev.gateway.entities.Pages;
import com.rimdev.gateway.ouputobject.pagesob;


@Service
public class PageExternalServ {
	
	@Autowired
	LangExternalServ langExternalServ;

	

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

				throw new NoResultException(langExternalServ.search("E400", langcode));

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoResultException(langExternalServ.search("E400", langcode));
		}
	}

	
}
