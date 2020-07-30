package com.rimdev.product.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.stereotype.Service;

import com.rimdev.product.Exceptions.PopupException;
import com.rimdev.product.Repo.MainCatalogRepo;
import com.rimdev.product.entities.MainCatalog;

@Service
public class MainCatalogServ {
	
	@Autowired
	MainCatalogRepo mainCatalogRepo;

	
	@Autowired
	TextConvertionServ textConvertionServ;
	
	public List<MainCatalog> getall(String langcode) {
		try {
			return (List<MainCatalog>) mainCatalogRepo.findAll();
			
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
