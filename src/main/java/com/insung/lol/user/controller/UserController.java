package com.insung.lol.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insung.lol.common.BaseController;

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
public class UserController extends BaseController {

	@GetMapping("/test")
	public void test() {
		log.info("테스트성공");
	}
}
