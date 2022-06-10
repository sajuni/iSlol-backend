package com.insung.lol.member.controller;

import com.insung.lol.auth.domain.UserDetail;
import com.insung.lol.auth.payload.request.JwtTokenReq;
import com.insung.lol.auth.payload.response.JwtResponse;
import com.insung.lol.auth.security.jwt.JwtUtils;
import com.insung.lol.common.BaseController;
import com.insung.lol.common.exception.BizException;
import com.insung.lol.member.domain.Member;
import com.insung.lol.member.service.MemberService;
import com.insung.lol.member.vo.SignInVO;
import com.insung.lol.member.vo.SignUpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value ="/api/auth")
public class MemberController extends BaseController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpVO signUpVO) {
        if (memberService.existsByMemberId(signUpVO.getMemberId())) {
            new BizException("signupError", "중복된 이메일 입니다.");
        }

        Member mem = new Member(signUpVO);
        Member result = memberService.signUpMember(mem);

        return getSaveEntity(result);
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInVO signInVO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInVO.getMemberId(), signInVO.getMemberPw())); // 아이디 패스워드 검증
        SecurityContextHolder.getContext().setAuthentication(authentication);  // 인증정보 저장
        String token = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefreshJwtToken(token);

        UserDetail user = (UserDetail) authentication.getPrincipal(); // 인증정보 매핑
        List<String> roles = user.getAuthorities().stream().map(o -> o.getAuthority()).collect(Collectors.toList());

        return getResponseEntity(new JwtResponse(user, roles, token, refreshToken));
    }

    @PostMapping(value = "/validatejwttoken")
    public ResponseEntity<?> validatejwt(@RequestBody JwtTokenReq jwtTokenReq) {
        HashMap<Object, Object> responseMap = new HashMap<>();
        try {
            if (jwtUtils.validateJwtToken(jwtTokenReq.getRefreshToken())) {
                responseMap.put("isValidateJwtToken", true);
                return getResponseEntity(responseMap);
            }
        } catch (Exception e) {
            log.error("JwtToken Exception", e);
        }

        responseMap.put("isValidateJwtToken", false);
        return getErrorEntity(responseMap);
    }


}
