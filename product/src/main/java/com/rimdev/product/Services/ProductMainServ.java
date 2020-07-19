package com.rimdev.product.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.product.Repo.ProductMainRepo;

@Service
public class ProductMainServ {
	
	@Autowired
	ProductMainRepo productMainRepo;

}
