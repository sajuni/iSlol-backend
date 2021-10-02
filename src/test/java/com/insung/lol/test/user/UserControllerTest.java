package com.insung.lol.test.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.insung.lol.user.controller.UserController;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(controllers = UserController.class)
@Slf4j
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void logTest() throws Exception {
		
		log.info("인포로그");
		log.debug("인포로그");
		log.trace("트레이스로그");
		log.error("에러로그");
		
	}
	
}
