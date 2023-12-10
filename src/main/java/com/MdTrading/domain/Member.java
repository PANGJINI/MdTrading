package com.MdTrading.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Member {
	@Id
	private String id;			//회원ID
	private String password;	//비밀번호
	private String name;		//닉네임
	private String phone;		//연락처
	private String email;		//이메일
}
