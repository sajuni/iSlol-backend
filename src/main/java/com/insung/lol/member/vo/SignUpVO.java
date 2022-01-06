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

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String name;

	@NotBlank
	@Size(min = 6, max = 40)
	private String pwd;

	private String addr;
	private Set<String> role;


}
