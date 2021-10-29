package com.insung.lol.auth.payload.request;

import lombok.Data;

/** 
* @packageName 	: com.insung.lol.auth.payload.request 
* @fileName 	: JwtTokenReq.java 
* @author 		: Seung Hyo
* @date 		: 2021.10.29 
* @description 	: 토큰 요청 VO
* =========================================================== 
* DATE 			AUTHOR 		NOTE 
* ----------------------------------------------------------- 
* 2021.10.29 	Seung Hyo 	최초 생성 
*/
@Data
public class JwtTokenReq {
	private String token;
	private String refreshtoken;
}
