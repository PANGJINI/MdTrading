package com.MdTrading.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MdTrading.domain.Member;
import com.MdTrading.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository memberRepository;
	
	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepository.findById(member.getId());
		if(findMember.isPresent())
			return findMember.get();
		else return null;
	}




}
