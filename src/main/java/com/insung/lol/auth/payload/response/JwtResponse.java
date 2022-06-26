package com.insung.lol.auth.payload.response;

import java.util.List;

import com.insung.lol.auth.domain.UserDetail;
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
	private Long seq;
	private String id;
	private String name;
	private String email;
	private String nick;
	private String mobileNum;
	private List<String> roles;
	private String token;
	private String refreshToken;

	public JwtResponse(UserDetail user, List<String> roles, String token, String refreshToken) {
		this.seq = user.getSeq();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.nick = user.getNick();
		this.mobileNum = user.getMobileNum();
		this.roles = roles;
		this.token = token;
		this.refreshToken = refreshToken;
	}
}
