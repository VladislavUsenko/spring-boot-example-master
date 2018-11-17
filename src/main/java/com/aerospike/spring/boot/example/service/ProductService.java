package com.aerospike.spring.boot.example.service;

import com.aerospike.spring.boot.example.domain.Product;
import org.springframework.validation.BindingResult;

public interface ProductService {
	Iterable<Product> listAllProducts();

	Product getProductById(Integer id);

	Product saveProduct(Product product);

	void deleteProduct(Integer id);
}