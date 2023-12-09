package com.MdTrading.service;

import java.util.List;

import com.MdTrading.domain.Product;

public interface ProductService {
	public void insertProduct(Product product);
	public List<Product> getProductList(Product product);
}
