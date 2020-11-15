package com.rimdev.rimnotif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rimdev.rimnotif.Config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class NotifApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifApplication.class, args);
	}

}