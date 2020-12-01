package com.rimdev.rimpriv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rimdev.rimpriv.Config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class PrivApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivApplication.class, args);
	}

}