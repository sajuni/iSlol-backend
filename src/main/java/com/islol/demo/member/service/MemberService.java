package com.islol.demo.member.service;

import com.islol.demo.member.dto.LoginReqDTO;
import com.islol.demo.member.dto.LoginResDTO;
import com.islol.demo.member.dto.RegisterReqDTO;
import com.islol.demo.member.dto.RegisterResDTO;
import com.islol.demo.member.entity.Member;

import java.util.List;

public interface MemberService {
    RegisterResDTO register(RegisterReqDTO request) throws Exception;

    LoginResDTO login(LoginReqDTO request);

    List<Member> getMemberList();

}
