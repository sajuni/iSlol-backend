package com.insung.lol.member;

import com.insung.lol.member.domain.Member;
import com.insung.lol.member.service.MemberService;
import com.insung.lol.member.vo.SignUpVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
public class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName(value = "회원가입테스트")
    @Transactional
    @Rollback(value = false)
    public void signUpTest() {
        SignUpVO signUpVO = new SignUpVO("test", "1", "name", "em", "nick", "tel");
        Member member = new Member(signUpVO);
        //Member result = memberService.save(member);
        //System.out.println("result = " + result);
    }

}
