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
	
	@Override
    public void addMember(Member member) {		//회원 추가
        memberRepository.save(member);
    }
	
	@Override
    public boolean isIdAvailable(String id) {	//아이디 중복체크
        return memberRepository.findById(id).isEmpty();
    }


}
