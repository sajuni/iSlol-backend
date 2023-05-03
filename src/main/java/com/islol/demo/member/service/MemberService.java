package com.islol.demo.member.service;

import com.islol.demo.member.dto.LoginReqDTO;
import com.islol.demo.member.dto.LoginResDTO;
import com.islol.demo.member.dto.RegisterReqDTO;
import com.islol.demo.member.dto.RegisterResDTO;

public interface MemberService {
    RegisterResDTO register(RegisterReqDTO request) throws Exception;

    LoginResDTO login(LoginReqDTO request);

}
