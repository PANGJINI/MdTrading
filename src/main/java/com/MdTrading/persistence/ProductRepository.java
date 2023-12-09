package com.MdTrading.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MdTrading.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
