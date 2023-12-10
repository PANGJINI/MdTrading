package com.MdTrading.service;

import java.util.List;

import com.MdTrading.domain.Product;

public interface ProductService {
	public void insertProduct(Product product);
	public void updateProduct(Product product);
	public void deleteProduct(Product product);
	public List<Product> getProductList();
	public Product getProductById(Product product);
	List<String> findAllCategories();
	List<Product> getProductListByCategory(String productCategory);
}


