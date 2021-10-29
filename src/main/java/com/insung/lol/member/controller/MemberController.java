package com.insung.lol.member.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insung.lol.auth.payload.request.JwtTokenReq;
import com.insung.lol.auth.payload.response.JwtResponse;
import com.insung.lol.auth.payload.response.MessageResponse;
import com.insung.lol.auth.security.jwt.JwtUtils;
import com.insung.lol.auth.security.service.UserDetailsImpl;
import com.insung.lol.common.BaseController;
import com.insung.lol.common.exception.BizException;
import com.insung.lol.member.domain.Member;
import com.insung.lol.member.domain.MemberERole;
import com.insung.lol.member.domain.MemberRoles;
import com.insung.lol.member.repository.MemberRepository;
import com.insung.lol.member.repository.MemberRoleRepository;
import com.insung.lol.member.vo.SignInVO;
import com.insung.lol.member.vo.SignUpVO;

import lombok.extern.slf4j.Slf4j;


/** 
* @packageName 	: com.insung.lol.member.controller 
* @fileName 	: MemberController.java 
* @author 		: Seung Hyo
* @date 		: 2021.10.29 
* @description 	: 회원 관련 컨트롤러
* =========================================================== 
* DATE 			AUTHOR 		NOTE 
* ----------------------------------------------------------- 
* 2021.10.29 	Seung Hyo 	최초 생성 
*/

@Slf4j
@RestController
@RequestMapping("/api/user")
public class MemberController extends BaseController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberRoleRepository memberRoleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	/** 
	* @methodName 	: registerUser 
	* @author 		: Seung Hyo 
	* @description 	: 회원가입
	* @date 		: 2021.10.29 
	* @param signUpReq
	* @return
	* @throws BizException 
	*/
	@PostMapping("/auth/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpVO signUpReq) throws BizException {
		if(memberRepository.existsByMemberEmail(signUpReq.getEmail())) {
			throw new BizException("signup001", "중복된 이메일 입니다.");
		}
		
		Member member = new Member(signUpReq.getEmail(), encoder.encode(signUpReq.getPwd()), 
				signUpReq.getName(), signUpReq.getAddr());
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
	
	/** 
	* @methodName 	: authenticateUser 
	* @author 		: Seung Hyo 
	* @description 	: 로그인
	* @date 		: 2021.10.29 
	* @param signInReq
	* @return 
	*/
	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInVO signInReq) {
		log.info("테스트" + signInReq);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signInReq.getMemberEmail(), signInReq.getMemberPwd()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);
		String refreshToken = jwtUtils.generateRefreshJwtToken(token);
		
		UserDetailsImpl boUserDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = boUserDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return getResponseEntity(new JwtResponse(
				boUserDetails.getUserSeq(), 
				boUserDetails.getUsername(), 
				boUserDetails.getUserRealName(),
				 roles,
				 token, refreshToken));
	}
	
	/** 
	* @methodName 	: validatejwt 
	* @author 		: Seung Hyo 
	* @description 	: 토큰 유효성 체크
	* @date 		: 2021.10.29 
	* @param request
	* @param jwtTokenReq
	* @return 
	*/
	@PostMapping(value = "/auth/validatejwttoken")
	public ResponseEntity<?> validatejwt(HttpServletRequest request, @RequestBody JwtTokenReq jwtTokenReq) {
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
			try {
				if(jwtUtils.validateJwtToken(jwtTokenReq.getRefreshtoken())) {
					responseMap.put("isValidateJwtToken", true);
					return getResponseEntity(responseMap);
				}
			} catch (Exception e) {
				log.error("JwtToken Exception", e);
			}
		responseMap.put("isValidateJwtToken", false);
		return getResponseEntity(responseMap);
	}
	
	/** 
	* @methodName 	: refreshToken 
	* @author 		: Seung Hyo 
	* @description 	: 리프레시 토큰으로 토큰 재발급
	* @date 		: 2021.10.29 
	* @param request
	* @return 
	*/
	@PostMapping(value = "/auth/refreshtoken")
	public ResponseEntity<?> refreshToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		System.out.println("테스트: " + headerAuth);
		if (StringUtils.isNotEmpty(headerAuth) && headerAuth.startsWith("Bearer ")) {
			String refreshToken = headerAuth.substring(7, headerAuth.length());
			try {
				if(jwtUtils.validateJwtToken(refreshToken)) {
					String newToken = jwtUtils.generateJwtToken(refreshToken);
					String newRefreshToken = jwtUtils.generateRefreshJwtToken(newToken);
					HashMap<String, String> tokens = new HashMap<String, String>();
					tokens.put("token", newToken);
					tokens.put("refreshToken", newRefreshToken);
					return getResponseEntity(tokens);
				} else {
					throw new BizException("refreshtokenE001", "토큰이 유효하지 않습니다");
				}
			} catch (Exception e) {
				log.error("refreshToken Exception", e);
			}
			
		} 
		
		return null;
	}
	
}
