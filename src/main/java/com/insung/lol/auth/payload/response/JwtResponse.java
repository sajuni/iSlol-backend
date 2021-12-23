package com.insung.lol.auth.payload.response;

import java.util.List;

import lombok.Data;

/**
* @packageName 	: com.insung.lol.auth.payload.response
* @fileName 	: JwtResponse.java
* @author 		: Seung Hyo
* @date 		: 2021.10.29
* @description 	: 토큰 response
* ===========================================================
* DATE 			AUTHOR 		NOTE
* -----------------------------------------------------------
* 2021.10.29 	Seung Hyo 	최초 생성
*/
@Data
public class JwtResponse {

	private String type = "Bearer";
	private Long id;
	private String email;
	private String userNm;
	private String addr;
	private List<String> roles;
	private String token;
	private String refreshToken;

	public JwtResponse(Long id, String email, String userNm, String addr, List<String> roles, String token,
			String refreshToken) {
		super();
		this.id = id;
		this.email = email;
		this.userNm = userNm;
		this.addr = addr;
		this.roles = roles;
		this.token = token;
		this.refreshToken = refreshToken;
	}

}
