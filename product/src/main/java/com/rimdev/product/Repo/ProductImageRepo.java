package com.rimdev.product.Repo;

import org.springframework.data.repository.CrudRepository;
import com.rimdev.product.entities.ProductImage;


public interface ProductImageRepo extends CrudRepository<ProductImage, Integer>{

}
