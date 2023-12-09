package com.MdTrading.controller;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.MdTrading.domain.Member;
import com.MdTrading.domain.Message;
import com.MdTrading.domain.Product;
import com.MdTrading.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//전체 게시글 보여주기
	@RequestMapping("/getProductList")
	public String getProductList(Product product, Model model) {
		model.addAttribute("productList", productService.getProductList(product));
		return "productList";
	}
	
	
	//글등록 화면으로 이동
    @GetMapping("/insertProduct")
    public String insertProductView(Model model, HttpSession session) {
    	//세션에서 회원 정보 가져오기
    	Member currentMember = (Member)session.getAttribute("member");
    	model.addAttribute("currentMember", currentMember);
    	
    	return "insertProduct";
    }
    
    
    //게시물 등록하기
    @PostMapping("/insertProduct")
    public ModelAndView insertProduct(Product product, ModelAndView mav, HttpSession session,
    		@RequestParam("imageFile") MultipartFile imageFile) {

    	//세션에서 회원 닉네임 가져와서 Product 테이블의 userName에 넣어주기
        Member currentMember = (Member) session.getAttribute("member");
        product.setUserName(currentMember.getName());
        
        //이미지파일 byte로 변환하여 저장
        try {
            product.setProductImage(imageFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            // 에러 처리 로직 추가
        }
    	
    	productService.insertProduct(product);
    	
    	// 게시물 등록 완료시 alert 메시지 보여주고 메인으로 이동 
    	mav.addObject("data", new Message("게시물 등록이 완료되었습니다.", "/"));
    	mav.setViewName("Message");
    			
    	return mav;
    }
    
    
    
   
}
