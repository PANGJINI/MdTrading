package com.MdTrading.service;

import com.MdTrading.domain.Member;

public interface MemberService {
	public Member getMember(Member member);
	public void addMember(Member member);	
	public boolean isIdAvailable(String id);
	
}
