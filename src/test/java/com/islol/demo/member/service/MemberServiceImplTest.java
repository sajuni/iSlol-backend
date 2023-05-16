package com.islol.demo.member.service;

import com.islol.demo.member.dto.LoginReqDTO;
import com.islol.demo.member.dto.LoginResDTO;
import com.islol.demo.member.dto.RegisterReqDTO;
import com.islol.demo.member.dto.RegisterResDTO;
import com.islol.demo.member.entity.MemberRole;
import com.islol.demo.member.enums.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

        registerReqDTO.setCurTier("브론즈1");
        registerReqDTO.setPreTier("실버1");
        registerReqDTO.setRoles(Collections.singletonList(MemberRole.builder().name("ROLE_USER").build()));
        int count = 1;
        //when
        for(int i = 1; i <= 20; i++) {
            if(count == 6) {
                count = 1;
            }
            switch (count) {
                case 1:
                    registerReqDTO.setMainPosition(Position.AD);
                    registerReqDTO.setSubPosition(Position.JG);
                    break;
                case 2:
                    registerReqDTO.setMainPosition(Position.SUP);
                    registerReqDTO.setSubPosition(Position.MID);
                    break;
                case 3:
                    registerReqDTO.setMainPosition(Position.SUP);
                    registerReqDTO.setSubPosition(Position.AD);
                    break;
                case 4:
                    registerReqDTO.setMainPosition(Position.AD);
                    registerReqDTO.setSubPosition(Position.TOP);
                    break;
                case 5:
                    registerReqDTO.setMainPosition(Position.MID);
                    registerReqDTO.setSubPosition(Position.SUP);
                    break;
            }
            registerReqDTO.setCurTier("브론즈" + count);
            registerReqDTO.setPreTier("실버" + count);
            registerReqDTO.setTopTier("다이아" + count);
            registerReqDTO.setPwd("1");
            registerReqDTO.setCheckedPwd("1");
            registerReqDTO.setName("테스트이름" + i);
            registerReqDTO.setNick("테스트닉네임" + i);
            registerReqDTO.setAccount("test" + i + "@test.com");
            RegisterResDTO result = memberService.register(registerReqDTO);
            count++;
        }

        //then
//        assertNotNull(result.getId());
//        assertEquals("테스트이름", result.getName());
//        assertEquals("테스트닉네임", result.getNick());
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