package com.tutorialspoint.spring.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorialspoint.spring.restful.entity.Product;
import com.tutorialspoint.spring.restful.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired(required = true)
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(productService.findAllProducts(),
			HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
		return new ResponseEntity<>(productService.saveorUpdateProduct(product),
			HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getOneProduct(@PathVariable Long id) throws Exception {
		return new ResponseEntity<Product>(productService.findProductById(id),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "delete sukses";
	}
}
