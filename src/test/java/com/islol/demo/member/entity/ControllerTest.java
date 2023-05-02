package com.islol.demo.member.entity;

import com.islol.demo.member.controller.MemberController;
import com.islol.demo.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebMvcTest(controllers = MemberController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    public void saveTest() throws Exception {
        // given
        Member member = Member.builder().nick("애니").pwd("1").nick("테테").build();
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/member/login")).andExpect(MockMvcResultMatchers.status().isOk());
        // then
    }

}
