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
	private Long memberSeq;
	private String memberId;
	private String memberName;
	private String memberEmail;
	private String memberNick;
	private String memberTel;
	private List<String> roles;
	private String token;
	private String refreshToken;

	public JwtResponse(UserDetail user, List<String> roles, String token, String refreshToken) {
		this.memberSeq = user.getMemberSeq();
		this.memberId = user.getMemberId();
		this.memberName = user.getMemberName();
		this.memberEmail = user.getMemberEmail();
		this.memberNick = user.getMemberNick();
		this.memberTel = user.getMemberTel();
		this.roles = roles;
		this.token = token;
		this.refreshToken = refreshToken;
	}
}
