package com.MdTrading.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MdTrading.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	//모든 카테고리 가져오기
	//@Query("SELECT DISTINCT product_Category FROM Product")
	@Query("SELECT DISTINCT productCategory FROM Product")
	List<String> findAllCategories();
	List<Product> findByProductCategory(String productCategory);
}
