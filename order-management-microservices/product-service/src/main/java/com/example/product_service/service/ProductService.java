package com.example.product_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;

@Service 
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct (Product product){
        return productRepository.save(product);
    }

    public boolean deleteProduct(Long id){
        if (!productRepository.existsById(id)){
            return false;
        }
        productRepository.deleteById(id);

        return true;
    }
}
