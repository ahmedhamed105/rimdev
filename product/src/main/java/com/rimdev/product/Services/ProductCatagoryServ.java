package com.rimdev.product.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.product.Repo.ProductCatagoryRepo;

@Service
public class ProductCatagoryServ {
	
	@Autowired
	ProductCatagoryRepo productCatagoryRepo;

}
