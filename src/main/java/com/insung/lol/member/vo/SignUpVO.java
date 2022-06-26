package com.insung.lol.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
* @packageName 		: com.insung.lol.member.vo
* @fileName 		: SignUpVO.java
* @author 			: Seung Hyo
* @date 			: 2021.10.17
* @description 		: 사용자 등록 VO
* ===========================================================
* DATE 					   AUTHOR 			NOTE *
-----------------------------------------------------------
* 2021.10.17 			   Seung Hyo 	    최초 생성
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpVO {

	private String id;
	private String pw;
	private String name;
	private String email;
	@NotBlank
	private String nick;
	private String mobileNum;
	private Set<String> role;

	public SignUpVO(String id, String pw, String name, String email, String nick, String mobileNum) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.nick = nick;
		this.mobileNum = mobileNum;
	}
}
