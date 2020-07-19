package com.rimdev.product.Repo;

import org.springframework.data.repository.CrudRepository;
import com.rimdev.product.entities.ProductComments;

public interface ProductCommentsRepo extends CrudRepository<ProductComments, Integer>{

}
