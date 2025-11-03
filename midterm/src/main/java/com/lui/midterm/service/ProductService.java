package com.lui.midterm.service;

import com.lui.midterm.dto.ProductDTO;
import com.lui.midterm.exception.ApiExceptionHandler;
import com.lui.midterm.model.Product;
import com.lui.midterm.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(int id){
        return repository.findById(id).orElse(null);
    }

    public Product save(ProductDTO product){
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setStock(product.getStock());
        newProduct.setUnit(product.getUnit());
        newProduct.setPrice(product.getPrice());
        return repository.save(newProduct);
    }

    public Product updateProduct(Product product, ProductDTO productDTO){
        product.setName(ProductDTO.getName());
        product.setDescription(ProductDTO.getDescription());
        product.setStock(ProductDTO.getStock());
        product.setUnit(ProductDTO.getUnit());
        product.setPrice(ProductDTO.getPrice());
        return repository.save(product);
    }

    public void deleteProduct(int id){
        repository.deleteById(id);
    }
}
