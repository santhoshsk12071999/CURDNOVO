package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("Product1");
        Page<ProductEntity> productPage = new PageImpl<>(Collections.singletonList(product), PageRequest.of(0, 1), 1);

        when(productRepository.findAll(PageRequest.of(0, 1))).thenReturn(productPage);

        Page<ProductEntity> result = productService.getAllProducts(0, 1);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void testGetProductById() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("Product1");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<ProductEntity> result = productService.getProductById(1L);
        assertTrue(result.isPresent());
        assertEquals("Product1", result.get().getName());
    }

    @Test
    void testUpdateProduct() {
        ProductEntity product = new ProductEntity();
        product.setId(1L);
        product.setName("UpdatedProduct");

        when(productRepository.save(product)).thenReturn(product);

        ProductEntity updatedProduct = productService.updateProduct(1L, product);
        assertNotNull(updatedProduct);
        assertEquals("UpdatedProduct", updatedProduct.getName());
    }
    
    

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}


