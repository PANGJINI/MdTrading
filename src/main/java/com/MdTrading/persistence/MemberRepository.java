package com.MdTrading.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MdTrading.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
