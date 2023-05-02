package com.islol.demo.member.service;

import com.islol.demo.member.dto.LoginReqDTO;
import com.islol.demo.member.dto.LoginResDTO;
import com.islol.demo.member.dto.RegisterReqDTO;

public interface MemberService {
    Long register(RegisterReqDTO request) throws Exception;

    LoginResDTO login(LoginReqDTO request);

}
