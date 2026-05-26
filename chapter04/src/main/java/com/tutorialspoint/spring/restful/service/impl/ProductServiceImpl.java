package com.tutorialspoint.spring.restful.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tutorialspoint.spring.restful.entity.Product;
import com.tutorialspoint.spring.restful.repository.ProductRepository;
import com.tutorialspoint.spring.restful.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product findProductById(Long id) {
		Product product = productRepository.findById(id).orElse(new Product());
		return product;
	}

	@Override
	public Product saveorUpdateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id).orElse(new Product());
		productRepository.delete(product);
	}
	
}
