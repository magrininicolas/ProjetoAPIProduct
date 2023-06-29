package com.nicolas.springprojfirst.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicolas.springprojfirst.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
