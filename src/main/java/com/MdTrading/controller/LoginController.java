package com.MdTrading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.MdTrading.domain.Member;
import com.MdTrading.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/loginView")
	public String loginhtml() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Member member, HttpSession session, Model model) {
		Member findMember = memberService.getMember(member);
		
		if(findMember == null) {
			//DB에 회원이 없는 경우
			model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
			return "login";
		} else if(!findMember.getPassword().equals(member.getPassword())) {
			//비밀번호가 일치하지 않는 경우
			model.addAttribute("errorMessage", "잘못된 비밀번호입니다.");
			return "login";
		} else {
			//아이디, 비밀번호가 일치하는 경우 세션에 저장
			session.setAttribute("member", findMember);
			return "redirect:/";
		}
	}
	

}
