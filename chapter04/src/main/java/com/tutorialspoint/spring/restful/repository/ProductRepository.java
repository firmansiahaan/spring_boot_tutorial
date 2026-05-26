package com.tutorialspoint.spring.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorialspoint.spring.restful.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
