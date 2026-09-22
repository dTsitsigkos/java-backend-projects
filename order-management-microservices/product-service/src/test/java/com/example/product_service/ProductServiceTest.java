package com.example.product_service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import com.example.product_service.service.ProductService;

public class ProductServiceTest {

    @Test
    void shouldCreateProduct() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        Product product = new Product(null, "Laptop", new BigDecimal("1200.00"));

        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertEquals(product, result);
        verify(productRepository).save(product);
    }

    @Test
    void shouldGetAllProducts() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        List<Product> products = List.of(
                new Product(1L, "Laptop", new BigDecimal("1200.00")),
                new Product(2L, "Mouse", new BigDecimal("30.00"))
        );

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }

    @Test
    void shouldGetProductById() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        Product product =
                new Product(1L, "Laptop", new BigDecimal("1200.00"));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals("Laptop", result.getName());
        verify(productRepository).findById(1L);
    }

    @Test
    void shouldReturnNullWhenProductDoesNotExist() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        when(productRepository.findById(999L)).thenReturn(Optional.empty());

        Product result = productService.getProductById(999L);

        assertNull(result);
        verify(productRepository).findById(999L);
    }

    @Test
    void shouldDeleteProduct() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        when(productRepository.existsById(1L)).thenReturn(true);

        boolean result = productService.deleteProduct(1L);

        assertTrue(result);
        verify(productRepository).existsById(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void shouldNotDeleteProductWhenProductDoesNotExist() {
        ProductRepository productRepository = mock(ProductRepository.class);
        ProductService productService = new ProductService(productRepository);

        when(productRepository.existsById(999L)).thenReturn(false);

        boolean result = productService.deleteProduct(999L);

        assertFalse(result);
        verify(productRepository).existsById(999L);
        verify(productRepository, never()).deleteById(999L);
    }
}