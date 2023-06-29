package com.nicolas.springprojfirst.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nicolas.springprojfirst.dtos.ProductRecordDTO;
import com.nicolas.springprojfirst.models.ProductModel;
import com.nicolas.springprojfirst.repositories.ProductRepository;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  public ProductModel saveProduct(ProductRecordDTO productRecordDTO) {
    ProductModel productModel = new ProductModel();
    BeanUtils.copyProperties(productRecordDTO, productModel);
    productRepository.save(productModel);
    return productModel;
  }

  public List<ProductModel> getAllProducts() {
    return productRepository.findAll();
  }

  public ResponseEntity<Object> getOneProduct(UUID id) {
    Optional<ProductModel> product = findProductById(id);
    if (product.isEmpty()) {
      return productNotFound(id);
    }
    return ResponseEntity.status(HttpStatus.FOUND).body(product.get());
  }

  public ResponseEntity<Object> updateProdut(UUID id, ProductRecordDTO productRecordDTO) {
    Optional<ProductModel> product = findProductById(id);
    if (product.isEmpty()) {
      return productNotFound(id);
    }
    ProductModel productModel = product.get();
    BeanUtils.copyProperties(productRecordDTO, productModel);
    return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
  }

  public ResponseEntity<Object> deleteProduct(UUID id) {
    Optional<ProductModel> product = findProductById(id);
    if (product.isEmpty()) {
      return productNotFound(id);
    }
    productRepository.delete(product.get());
    return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
  }

  public Optional<ProductModel> findProductById(UUID id) {
    return productRepository.findById(id);
  }

  public ResponseEntity<Object> productNotFound(UUID id) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found in DB");
  }

}
