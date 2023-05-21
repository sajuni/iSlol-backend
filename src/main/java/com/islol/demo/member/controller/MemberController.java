package com.islol.demo.member.controller;

import com.islol.demo.member.dto.LoginReqDTO;
import com.islol.demo.member.dto.RegisterReqDTO;
import com.islol.demo.member.entity.Member;
import com.islol.demo.member.service.MemberService;
import com.islol.demo.util.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController extends BaseController {

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> signIn(@Valid @RequestBody RegisterReqDTO request) throws Exception {
        return getCreateResponseEntity(memberService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> signUp(@RequestBody LoginReqDTO request) {
        return getResponseEntity(memberService.login(request));
    }

    @GetMapping("/member-list")
    public List<Member> getMemberList () {
        return memberService.getMemberList();
    }

}
