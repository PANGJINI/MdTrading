package com.MdTrading.controller;



import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		//카테고리가 지정되어 있으면 해당 카테고리의 상품 목록을 가져오고, 그렇지 않으면 전체 상품 목록을 가져옴
		List<Product> productList;
		if (productCategory != null) {
            productList = productService.getProductListByCategory(productCategory);
        } else {
            productList = productService.getProductList();
        }
		model.addAttribute("productList", productList);
		model.addAttribute("categories", productService.findAllCategories());
	       
		return "productList";
	}
	
	
    //게시물 상세 정보
    @GetMapping("/getProduct")
    public String getProductDetail(Product product, Model model) {
    	model.addAttribute("product", productService.getProductById(product));
    	return "productDetail";
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
    		@RequestParam("uploadFile") MultipartFile uploadFile) throws Exception{

    	//세션에서 회원 ID 가져와서 Product 테이블의 userName에 넣어주기
        Member currentMember = (Member) session.getAttribute("member");
        product.setUserName(currentMember.getId());
        
        //파일업로드처리
    	if(!uploadFile.isEmpty()) {
    		//원본 파일명 가져오기
    		String fileName =uploadFile.getOriginalFilename();
    		//지정된 경로에 파일 저장
    		uploadFile.transferTo(new File("D:/spring_workspace/MD-trading/src/main/resources/static/fileio/"+fileName));
    		//DB에 이미지파일 경로 저장
    		product.setImagePath("/fileio/"+fileName);
    	}
    
    	productService.insertProduct(product);
    	
    	// 게시물 등록 완료시 alert 메시지 보여주고 메인으로 이동 
    	mav.addObject("data", new Message("게시물 등록이 완료되었습니다.", "/"));
    	mav.setViewName("Message");
    			
    	return mav;
    }
    
    
    //글 수정 화면으로 이동
    @GetMapping("/updateProduct")
    public String updateProductView(Product product, Model model, HttpSession session) {
    	//세션에서 회원 정보 가져오기
    	Member currentMember = (Member)session.getAttribute("member");
    	model.addAttribute("currentMember", currentMember);
    	//수정할 상품 정보를 model에 추가
    	model.addAttribute("product", productService.getProductById(product));
    	return "updateProduct";
    }
    
    //게시물 수정
    @PostMapping("/updateProduct")
    public String updateProduct(Product product) {
    	productService.updateProduct(product);
    	return "redirect:/";
    } 
    
    //게시물 삭제
    @GetMapping("/deleteProduct")
    public String deleteProduct(Product product) {
    	productService.deleteProduct(product);
    	return "redirect:/";
    }
    
    
   
    
    
   
}
