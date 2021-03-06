package com.rimdev.rimlang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rimdev.rimlang.Config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class LangApplication {

	public static void main(String[] args) {
		SpringApplication.run(LangApplication.class, args);
	}

}