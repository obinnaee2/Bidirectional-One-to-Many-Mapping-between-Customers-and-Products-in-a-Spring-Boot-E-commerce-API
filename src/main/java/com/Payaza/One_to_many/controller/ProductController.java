package com.Payaza.One_to_many.controller;

import com.Payaza.One_to_many.entity.Product;
import com.Payaza.One_to_many.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + productId + " not found.");
        }
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable int productId, @RequestBody Product productDetails) {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();
            productToUpdate.setProductName(productDetails.getProductName());
            productToUpdate.setProductDescription(productDetails.getProductDescription());
            productToUpdate.setProductPrice(productDetails.getProductPrice());
            return ResponseEntity.ok(productRepository.save(productToUpdate));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + productId + " not found.");
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
        Optional<Product> productToDelete = productRepository.findById(productId);
        if (productToDelete.isPresent()) {
            productRepository.delete(productToDelete.get());
            return ResponseEntity.noContent().build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + productId + " not found.");
        }
    }
}
