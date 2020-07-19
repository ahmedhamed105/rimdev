package com.rimdev.product.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rimdev.product.Repo.SubCatalogRepo;

@Service
public class SubCatalogServ {
	
	@Autowired
	SubCatalogRepo subCatalogRepo;

}
