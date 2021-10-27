package com.insung.lol.member.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insung.lol.auth.payload.response.MessageResponse;
import com.insung.lol.common.BaseController;
import com.insung.lol.common.exception.BizException;
import com.insung.lol.member.domain.Member;
import com.insung.lol.member.domain.MemberERole;
import com.insung.lol.member.domain.MemberRoles;
import com.insung.lol.member.repository.MemberRepository;
import com.insung.lol.member.repository.MemberRoleRepository;
import com.insung.lol.member.vo.SignUpVO;

import lombok.extern.slf4j.Slf4j;


/** 
* @packageName 		: com.insung.lol.member.controller 
* @fileName 		: MemberController.java 
* @author 			: Seung Hyo 
* @date 			: 2021.10.17 
* @description 		: 사용자 관련 컨트롤러
* =========================================================== 
* DATE 					   AUTHOR 			NOTE * 
----------------------------------------------------------- 
* 2021.10.17 			   Seung Hyo 	    최초 생성 
*/
@Slf4j
@RestController
@RequestMapping("/api/user")
public class MemberController extends BaseController {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	MemberRoleRepository memberRoleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	/**
	 * @methodName 	: registerUser
	 * @author 		: Seung Hyo
	 * @date 		: 2021.10.17 
	 * @param signUpReq
	 * @return
	 * @throws BizException
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpVO signUpReq) throws BizException {
		if(memberRepository.existsByMemberEmail(signUpReq.getMemberEmail())) {
			throw new BizException("signup001", "중복된 이메일 입니다.");
		}
		
		Member member = new Member(signUpReq.getMemberEmail(), encoder.encode(signUpReq.getMemberPwd()), 
				signUpReq.getMemberName(), signUpReq.getMemberAddr(), "U");
		Set<String> strRoles = signUpReq.getRole();
		Set<MemberRoles> memberRoles = new HashSet<>();
		
		if (strRoles == null) {
			MemberRoles memberRole = memberRoleRepository.findByRoleName(MemberERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			memberRoles.add(memberRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					MemberRoles adminRole = memberRoleRepository.findByRoleName(MemberERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					memberRoles.add(adminRole);

					break;
				default:
					MemberRoles userRole = memberRoleRepository.findByRoleName(MemberERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					memberRoles.add(userRole);
				}
			});
		}
		
		member.setRoles(memberRoles);
		memberRepository.save(member);
		
		return getResponseEntity(new MessageResponse("회원가입 성공!"));
		
	}
	
}
