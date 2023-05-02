package com.islol.demo.member.service;

import com.islol.demo.member.dto.LoginReqDTO;
import com.islol.demo.member.dto.LoginResDTO;
import com.islol.demo.member.dto.RegisterReqDTO;
import com.islol.demo.member.entity.Member;
import com.islol.demo.member.entity.MemberRole;
import com.islol.demo.member.repository.MemberRepository;
import com.islol.demo.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Transactional
    public Long register(RegisterReqDTO request) throws Exception {

        if (memberRepository.findByAccount(request.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if (!request.getPwd().equals(request.getCheckedPwd())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        request.setPwd(passwordEncoder.encode(request.getPwd()));
        Member member = request.toEntity();
        member.setRoles(Collections.singletonList(MemberRole.builder().name("ROLE_USER").build()));
        Member result = memberRepository.save(member);

        return result.getId();
    }

    @Transactional
    public LoginResDTO login(LoginReqDTO request) {
        Member member = memberRepository.findByAccount(request.getEmail()).orElseThrow(() ->
            new BadCredentialsException("아이디를 확인해 주세요.")
        );
        if (!passwordEncoder.matches(request.getPwd(), member.getPwd())) {
            throw new BadCredentialsException("비밀번호를 확인해 주세요.");
        }

        return LoginResDTO.builder()
                .id(member.getId())
                .account(member.getAccount())
                .nick(member.getNick())
                .name(member.getName())
                .roles(member.getRoles())
                .token(jwtProvider.createToken(member.getAccount(), member.getRoles()))
                .build();
    }
}
