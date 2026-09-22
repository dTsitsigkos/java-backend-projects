package com.example.product_service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldSaveAndFindProduct() {
        Product product = new Product(null, "Laptop", new BigDecimal("1200.00"));

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());

        Optional<Product> result = productRepository.findById(savedProduct.getId());

        assertTrue(result.isPresent());
        assertEquals("Laptop", result.get().getName());
        assertEquals( new BigDecimal("1200.00"), result.get().getPrice());
    }

    @Test
    void shouldFindAllProducts() {
        Product laptop = new Product(null, "Laptop", new BigDecimal("1200.00"));

        Product mouse = new Product(null, "Mouse", new BigDecimal("30.00"));

        productRepository.save(laptop);
        productRepository.save(mouse);

        List<Product> products = productRepository.findAll();

        assertEquals(2, products.size());
    }

    @Test
    void shouldDeleteProduct() {
        Product product = new Product(null, "Laptop", new BigDecimal("1200.00"));

        Product savedProduct = productRepository.save(product);

        Long id = savedProduct.getId();

        productRepository.deleteById(id);

        Optional<Product> result = productRepository.findById(id);

        assertTrue(result.isEmpty());
    }
}