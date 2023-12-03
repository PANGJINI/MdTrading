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
	private String id;
	private String password;
	private String name;
}
