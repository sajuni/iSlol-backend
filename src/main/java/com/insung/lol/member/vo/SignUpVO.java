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

	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	@NotBlank
	private String memberNick;
	private String memberTel;
	private Set<String> role;

	public SignUpVO(String memberId, String memberPw, String memberName, String memberEmail, String memberNick, String memberTel) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberNick = memberNick;
		this.memberTel = memberTel;
	}
}
