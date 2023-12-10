package com.MdTrading.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue
	private int productId;				//상품 번호
	private String productTitle;		//상품 제목
	private String productCategory;		//상품 카테고리
	private String userName;			//등록자 닉네임
	private String productPrice;		//상품 가격
	private String productQuantity;		//상품 수량
	private String productContent;		//상품 설명
	private Date productDate = new Date();	//상품 등록일
	private String imagePath; // 이미지 파일 경로를 저장할 필드 추가
	//@Lob
    //private byte[] productImage; 		//상품 이미지 (바이트 배열로 저장)
    @Transient
    private MultipartFile imageFile; 	//업로드된 이미지를 임시로 저장하는 필드

}
