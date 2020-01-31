package com.rimdev.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.rimdev.user.Config.FileStorageProperties;
import com.rimdev.user.Config.WebConfiguration;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
