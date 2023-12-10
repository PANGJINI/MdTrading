package com.MdTrading.domain;

import lombok.Data;

@Data
public class Message {	//alert 창에 보여줄 메시지 클래스
	String message = "";
	String href = "";
	
	public Message(String message, String href) {
		this.message = message;
		this.href = href;
	}
}
