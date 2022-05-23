package com.insung.lol.member.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignInVO {
	@NotBlank
	private String memberId;
	@NotBlank
	private String memberPw;
}
