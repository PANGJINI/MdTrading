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
	
	//전체 상품 보여주기
	public List<Product> getProductList() {
		return (List<Product>)productRepo.findAll();
	}

	// 상품 상세 정보 가져오기
    public Product getProductById(int productId) {
        return productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다. ID: " + productId));
    }
	
}
