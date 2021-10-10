package com.insung.lol.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insung.lol.common.BaseController;
import com.insung.lol.member.domain.Member;
import com.insung.lol.member.service.MemberService;
import com.insung.lol.member.vo.SignVo;

import lombok.extern.slf4j.Slf4j;

/**
 * @fileName	: BackOfficeUserController.java
 * @author		: "sajuni11"
 * @date		: 2021. 8. 7.
 * @description	: 사용자 관련 컨트롤러(로그인)
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class MemberController extends BaseController {

	@Autowired
	MemberService memberService;
	
	@PostMapping("/sign")
	public void saveUser(@RequestBody SignVo signVo) {
		Member member = Member.builder()
					.memberEmail(signVo.getEamil())
					.memberPwd(signVo.getPassword())
					.memberName(signVo.getName())
					.build();
		memberService.save(member);
	}
	
}
