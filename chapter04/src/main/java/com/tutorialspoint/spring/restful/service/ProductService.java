package com.tutorialspoint.spring.restful.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tutorialspoint.spring.restful.entity.Product;

@Service
public interface ProductService {

	List<Product> findAllProducts();
	Product findProductById(Long id);
	Product saveorUpdateProduct(Product product);
	void deleteProduct(Long id);
	
}
