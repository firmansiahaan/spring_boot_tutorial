package com.tutorialspoint.spring.restful.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nama;
	
	@Column(nullable = false)
	private Integer hargaBeli;
	
	@Column(nullable = false)
	private Integer hargaJual;
	
	public Product() {
		nama = "";
		hargaBeli = 0;
		hargaJual = 0;
	};
	
	public Product(String nama, Integer hargaBeli, Integer hargaJual) {
		this.nama = nama;
		this.hargaBeli = hargaBeli;
		this.hargaJual = hargaJual;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Integer getHargaBeli() {
		return hargaBeli;
	}

	public void setHargaBeli(Integer hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public Integer getHargaJual() {
		return hargaJual;
	}

	public void setHargaJual(Integer hargaJual) {
		this.hargaJual = hargaJual;
	};
	
	
}
