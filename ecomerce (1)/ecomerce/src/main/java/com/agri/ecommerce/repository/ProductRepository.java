package com.agri.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agri.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
