package com.sup.gateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sup.gateway.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
