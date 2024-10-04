package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.entity.ProductEntity;

public interface ProductService {

	List<ProductEntity> getAllProducts();

	Optional<ProductEntity> getProductById(Long id);

	ProductEntity createProduct(ProductEntity product);

	ProductEntity updateProduct(Long id, ProductEntity product);

	void deleteProduct(Long id);

	Page<ProductEntity> getAllProducts(int page, int size);

	
}
