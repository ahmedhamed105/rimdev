package com.rimdev.product.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.product.Exceptions.PopupException;
import com.rimdev.product.Repo.ProductMainRepo;
import com.rimdev.product.entities.ProductMain;

@Service
public class ProductMainServ {
	
	@Autowired
	ProductMainRepo productMainRepo;
	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	public List<ProductMain> getall(String langcode) {
		try {
			return (List<ProductMain>) productMainRepo.findAll();
			
} catch (TransientDataAccessException  se) {
	se.printStackTrace();
	throw new PopupException(textConvertionServ.search("E104", langcode));
} catch (RecoverableDataAccessException  se) {
	se.printStackTrace();
	throw new PopupException(textConvertionServ.search("E104", langcode));
}catch (ScriptException  se) {
	se.printStackTrace();
	throw new PopupException(textConvertionServ.search("E104", langcode));
}catch (NonTransientDataAccessException  se) {
	se.printStackTrace();
	throw new PopupException(textConvertionServ.search("E104", langcode));
}
		
	}


}
