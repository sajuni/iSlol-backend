package com.insung.lol.test.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.insung.lol.member.controller.MemberController;
import com.insung.lol.member.vo.SignUpVO;

@SpringBootTest
@WebMvcTest(controllers = MemberController.class)
public class MemberControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void sginUpTest() throws Exception {
		SignUpVO dto = new SignUpVO("sekne@naver.com", "강테스트", "1212", {"user"});
		mvc.perform(post("/signup"))
			.andExpect(status().isOk())
			
}
