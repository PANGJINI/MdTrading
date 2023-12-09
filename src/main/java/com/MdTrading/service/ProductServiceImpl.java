package com.MdTrading.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MdTrading.domain.Product;
import com.MdTrading.persistence.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	//상품 올리기
	public void insertProduct(Product product) {
		productRepo.save(product);
	}
	
	public List<Product> getProductList(Product product) {
		return (List<Product>)productRepo.findAll();
	}

}
