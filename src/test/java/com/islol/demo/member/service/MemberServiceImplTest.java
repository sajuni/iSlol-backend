package com.islol.demo.member.service;

import com.islol.demo.member.dto.LoginReqDTO;
import com.islol.demo.member.dto.LoginResDTO;
import com.islol.demo.member.dto.RegisterReqDTO;
import com.islol.demo.member.dto.RegisterResDTO;
import com.islol.demo.member.entity.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("멤버 세이브 테스트")
    void saveMemberTest() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();
        registerReqDTO.setTier("브론즈1");
        registerReqDTO.setName("테스트이름");
        registerReqDTO.setNick("테스트닉네임");
        registerReqDTO.setPwd("1");
        registerReqDTO.setCheckedPwd("1");
        registerReqDTO.setAccount("test@test.com");
        registerReqDTO.setRoles(Collections.singletonList(MemberRole.builder().name("ROLE_USER").build()));

        //when
        RegisterResDTO result = memberService.register(registerReqDTO);

        //then
        assertNotNull(result.getId());
        assertEquals("테스트이름", result.getName());
        assertEquals("테스트닉네임", result.getNick());
    }

    @Test
    @DisplayName("로그인 테스트")
    void loginTest() {
        //given
        LoginReqDTO loginReqDTO = new LoginReqDTO();
        loginReqDTO.setAccount("test@test.com");
        loginReqDTO.setPwd("1");

        //when
        LoginResDTO result = memberService.login(loginReqDTO);

        //then
        assertNotNull(result.getId());
        assertEquals("test@test.com", result.getAccount());

    }

}