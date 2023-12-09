package com.MdTrading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.MdTrading.domain.Member;
import com.MdTrading.domain.Message;
import com.MdTrading.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SignUpController {
	@Autowired
	private MemberService memberService;
	
	// 회원가입 화면 열기
	@RequestMapping("/signUpView")
	public String signUpHtml() {
		return "signUp";
	}
	
	// 회원가입 하기
	@PostMapping("/signUp")
	public ModelAndView signUp(@RequestParam(name = "id") String id,
		    @RequestParam(name = "password") String password,
		    @RequestParam(name = "name") String name,
		    @RequestParam(name = "phone") String phone,
		    @RequestParam(name = "phone_m") String phoneM,
		    @RequestParam(name = "phone_e") String phoneE,
		    @RequestParam(name = "email") String email,
		    ModelAndView mav) {
		
		// 전화번호 입력내용 '-' 없이 가져오기
	    String ph = phone + phoneM + phoneE;
	    
	    Member member = new Member();
	    member.setId(id);
	    member.setPassword(password);
	    member.setName(name);
	    member.setPhone(ph);
	    member.setEmail(email);
	    
	 
		// 회원가입 서비스 호출하고 db에 회원 추가
		memberService.addMember(member);
		
		// 회원가입 완료되면 alert 메시지 보여주고 메인으로 이동 
		mav.addObject("data", new Message("회원가입이 완료되었습니다.", "/"));
		mav.setViewName("Message");
		
		return mav; 
		
	}
	
	

}
