package com.islol.demo.member.entity;

import com.islol.demo.member.dto.LoginReqDTO;
import com.islol.demo.member.dto.LoginResDTO;
import com.islol.demo.member.dto.RegisterReqDTO;
import com.islol.demo.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberTest {

    @Autowired
    MemberServiceImpl memberServiceImpl;
    @Test
    void saveTest() throws Exception {
        RegisterReqDTO request = new RegisterReqDTO();
        request.setName("테스트");
        request.setEmail("테스트");
        request.setPwd("테스트");
        request.setCheckedPwd("테스트");
        request.setTel("테스트");
        request.setNick("테스트");
        memberServiceImpl.register(request);
    }

    @Test
    void signInTest() {
        LoginReqDTO signInReqDTO = new LoginReqDTO();
        signInReqDTO.setEmail("1");
        signInReqDTO.setPwd("1");
        LoginResDTO signInResDTO = memberServiceImpl.login(signInReqDTO);
        System.out.println("signInResDTO = " + signInResDTO.getRoles().get(0).getName());
    }
}