package com.MdTrading.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MdTrading.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	//카테고리를 가져오는 쿼리
	@Query("SELECT DISTINCT productCategory FROM Product")
	List<String> findAllCategories();
	
	//카테고리별로 상품을 찾기 위한 쿼리
	List<Product> findByProductCategory(String productCategory);
}
