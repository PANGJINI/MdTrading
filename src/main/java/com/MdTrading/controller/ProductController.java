package com.MdTrading.controller;



import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//카테고리 별로 게시글 보여주기
	@RequestMapping("/getProductList")
	public String getProductList(@RequestParam(name = "productCategory", required = false)
									String productCategory, Model model) {
		List<Product> productList;
		if (productCategory != null) {
            productList = productService.getProductListByCategory(productCategory);
        } else {
            productList = productService.getProductList();
        }
		model.addAttribute("productList", productList);
		//model.addAttribute("productList", productService.getProductList());
		//model.addAttribute("categories", productService.findAllCategories());
		model.addAttribute("categories", productService.findAllCategories());
	       
		return "productList";
	}
	
	
    //게시물 상세 정보
    @GetMapping("/getProduct")
    public String getProductDetail(Product product, Model model) {
    	model.addAttribute("product", productService.getProductById(product));
    	return "productDetail";
    }
//    public String productDetail(@PathVariable int productId, Model model) {
//    	
//        // productId에 해당하는 제품 정보를 데이터베이스에서 가져오기
//        Product product = productService.getProductById(productId);
//
//        // 제품 정보를 모델에 추가하여 뷰로 전달
//        model.addAttribute("product", product);
//
//        // 상세 페이지 뷰의 이름 반환
//        return "productDetail";
//    }
    
    
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

    	//세션에서 회원 ID 가져와서 Product 테이블의 userName에 넣어주기
        Member currentMember = (Member) session.getAttribute("member");
        product.setUserName(currentMember.getId());
        
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
    
    
    //게시물 수정
    @PostMapping("/updateProduct")
    public String updateProduct(Product product) {
    	productService.updateProduct(product);
    	return "/";
    } 
    
    //게시물 삭제
    @GetMapping("/deleteProduct")
    public String deleteProduct(Product product) {
    	productService.deleteProduct(product);
    	return "redirect:/";
    }
    
    
   
    
    
   
}
