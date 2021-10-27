package com.insung.lol.auth.payload.response;

import lombok.Data;

/** 
* @packageName 		: com.insung.lol.auth.payload.response 
* @fileName 		: MessageResponse.java 
* @author 			: Seung Hyo 
* @date 			: 2021.10.17 
* @description 		: 메세지 응답 VO
* =========================================================== 
* DATE 					   AUTHOR 			NOTE * 
----------------------------------------------------------- 
* 2021.10.17 			   Seung Hyo 	    최초 생성 
*/
@Data
public class MessageResponse {
	private String message;
	
	public MessageResponse(String message) {
		this.message = message;
	}
}
