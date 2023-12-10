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
	
	//상품 판매 게시글 올리기
	public void insertProduct(Product product) {
		productRepo.save(product);
	}
	
	//게시글 수정하기
	public void updateProduct(Product product) {
		 //Product productfind = productRepo.findById(product.getProductId()).get();
		 //ID로 상품 찾기
		 Product productfind = productRepo.findById(product.getProductId()).orElse(null);
		 if (productfind != null) {
			 // 상품을 찾았을 때만 업데이트 수행
			 productfind.setProductTitle(product.getProductTitle());
			 productfind.setProductCategory(product.getProductCategory());
			 productfind.setProductPrice(product.getProductPrice());
			 productfind.setProductQuantity(product.getProductQuantity());
			 productfind.setProductContent(product.getProductContent());
			 productfind.setImagePath(product.getImagePath());
			 
			 productRepo.save(productfind);
		    } 
		 
	 }
	
	//게시글 삭제하기
	 public void deleteProduct(Product product) {
		 //ID로 상품 삭제
		 productRepo.deleteById(product.getProductId());
	 }
	
	//전체 게시글 가져오기
	public List<Product> getProductList() {
		return (List<Product>)productRepo.findAll();
	}

	//ID로 게시글 상세 정보 가져오기
    public Product getProductById(Product product) {
        return productRepo.findById(product.getProductId()).get();
                //.orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다. ID: " + productId));
    }
    
    //카테고리별로 상품 목록 가져오기
    public List<Product> getProductListByCategory(String productCategory) {
        return productRepo.findByProductCategory(productCategory);
    }
    
    //모든 상품의 카테고리 가져오기
    public List<String> findAllCategories() {
    	return productRepo.findAllCategories();
    }
	
}
