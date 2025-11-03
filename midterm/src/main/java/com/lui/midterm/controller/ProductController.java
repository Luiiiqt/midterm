package com.lui.midterm.controller;

import com.lui.midterm.dto.ProductDTO;
import com.lui.midterm.model.Product;
import com.lui.midterm.repository.ProductRepository;
import com.lui.midterm.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.findAll();
    }

    @PostMapping("/products")
    public Product newProduct(@Valid @RequestBody ProductDTO product){
        return productService.save(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable int id, @Valid @RequestBody ProductDTO product){
        Product updateProduct = productService.findById(id);
        if (updateProduct == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID "+ id + " not found.");
        }
        return productService.updateProduct(updateProduct, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteCar(@PathVariable int id){
        if(productService.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID "+ id + " not found.");
        }
        productService.deleteProduct(id);
    }

}

