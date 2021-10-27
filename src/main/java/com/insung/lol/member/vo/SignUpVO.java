package com.insung.lol.member.vo;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

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
public class SignUpVO {
	
	public SignUpVO(String string, String string2, String string3, String string4) {
		// TODO Auto-generated constructor stub
	}
	@NotBlank
	@Email
	private String memberEmail;
	
	@NotBlank
	private String memberName;
	
	@NotBlank
	@Size(min = 6, max = 40)
	private String MemberPwd;

	private String MemberAddr;
	private Set<String> role;
}
