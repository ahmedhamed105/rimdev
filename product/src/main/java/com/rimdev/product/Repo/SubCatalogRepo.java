package com.rimdev.product.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.rimdev.product.entities.SubCatalog;

public interface SubCatalogRepo extends CrudRepository<SubCatalog, Integer>{
	
	@Query(value ="SELECT * FROM rim_product.sub_catalog where Main_catalog_ID =?1" , nativeQuery = true)
	Iterable<SubCatalog> getbymaincatg(int compid);

}
