package com.islol.demo.member.service;

import com.islol.demo.member.dto.LoginReqDTO;
import com.islol.demo.member.dto.LoginResDTO;
import com.islol.demo.member.dto.RegisterReqDTO;
import com.islol.demo.member.dto.RegisterResDTO;
import com.islol.demo.member.entity.MemberRole;
import com.islol.demo.member.enums.Position;
import com.islol.demo.member.enums.Tier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("멤버 세이브 테스트")
    void saveMemberTest() throws Exception {
        //given
        RegisterReqDTO registerReqDTO = new RegisterReqDTO();

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
                    registerReqDTO.setTopTier(Tier.CHALLENGER);
                    registerReqDTO.setCurTier(Tier.IRON);
                    registerReqDTO.setPreTier(Tier.DIAMOND);
                    break;
                case 2:
                    registerReqDTO.setMainPosition(Position.SUP);
                    registerReqDTO.setSubPosition(Position.MID);
                    registerReqDTO.setTopTier(Tier.MASTER);
                    registerReqDTO.setCurTier(Tier.GOLD);
                    registerReqDTO.setPreTier(Tier.DIAMOND);
                    break;
                case 3:
                    registerReqDTO.setMainPosition(Position.SUP);
                    registerReqDTO.setSubPosition(Position.AD);
                    registerReqDTO.setTopTier(Tier.GRANDMASTER);
                    registerReqDTO.setCurTier(Tier.BRONZE);
                    registerReqDTO.setPreTier(Tier.SILVER);
                    break;
                case 4:
                    registerReqDTO.setMainPosition(Position.AD);
                    registerReqDTO.setSubPosition(Position.TOP);
                    registerReqDTO.setTopTier(Tier.DIAMOND);
                    registerReqDTO.setCurTier(Tier.PLATINUM);
                    registerReqDTO.setPreTier(Tier.GOLD);
                    break;
                case 5:
                    registerReqDTO.setMainPosition(Position.MID);
                    registerReqDTO.setSubPosition(Position.SUP);
                    registerReqDTO.setTopTier(Tier.SILVER);
                    registerReqDTO.setCurTier(Tier.IRON);
                    registerReqDTO.setPreTier(Tier.SILVER);
                    break;
            }
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
        loginReqDTO.setAccount("test1@test.com");
        loginReqDTO.setPwd("1");

        //when
        LoginResDTO result = memberService.login(loginReqDTO);

        //then
        assertNotNull(result.getId());
        assertEquals("test1@test.com", result.getAccount());

    }

}