package com.nicolas.springprojfirst.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicolas.springprojfirst.dtos.ProductRecordDTO;
import com.nicolas.springprojfirst.models.ProductModel;
import com.nicolas.springprojfirst.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  ProductService productService;

  @PostMapping
  public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRecordDTO));
  }

  @GetMapping
  public ResponseEntity<List<ProductModel>> getAllProducts() {
    return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getOneProduct(@PathVariable("id") UUID id) {
    return productService.getOneProduct(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateProdut(@PathVariable("id") UUID id,
      @RequestBody @Valid ProductRecordDTO productRecordDTO) {
    return productService.updateProdut(id, productRecordDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteProduct(@PathVariable("id") UUID id){
    return productService.deleteProduct(id);
  }

}
