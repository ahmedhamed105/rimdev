package com.rimdev.rimuser.Services;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rimdev.rimuser.outputobject.langob;


@Service
public class LangExternalServ {
	
	

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

	
}
