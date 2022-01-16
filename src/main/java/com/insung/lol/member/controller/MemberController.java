package com.insung.lol.member.controller;

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
import com.insung.lol.member.dto.MemberUpdateDTO;
import com.insung.lol.member.repository.MemberRoleRepository;
import com.insung.lol.member.service.MemberService;
import com.insung.lol.member.vo.SignInVO;
import com.insung.lol.member.vo.SignUpVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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
@RequestMapping("/api/user/auth")
public class MemberController extends BaseController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRoleRepository memberRoleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private ModelMapper modelMapper;

	/**
	* @methodName 	: registerUser
	* @author 		: Seung Hyo
	* @description 	: 회원가입
	* @date 		: 2021.10.29
	* @param signUpReq
	* @return
	* @throws BizException
	*/
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpVO signUpReq) throws BizException {
		if(memberService.existsByMemberEmail(signUpReq.getEmail())) {
			throw new BizException("signup001", "중복된 이메일 입니다.");
		}
		Member member = new Member(signUpReq.getEmail(), encoder.encode(signUpReq.getPwd()),
				signUpReq.getName(), signUpReq.getAddr(), signUpReq.getNick());
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

		memberService.save(member);

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
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInVO signInReq) {
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
				boUserDetails.getAddr(),
			    boUserDetails.getUserNickName(),
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
	@PostMapping(value = "/validatejwttoken")
	public ResponseEntity<?> validatejwt(HttpServletRequest request, @RequestBody JwtTokenReq jwtTokenReq) {
		HashMap<String, Object> responseMap = new HashMap<>();
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
	@PostMapping(value = "/refreshtoken")
	public ResponseEntity<?> refreshToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		if (StringUtils.isNotEmpty(headerAuth) && headerAuth.startsWith("Bearer ")) {
			String refreshToken = headerAuth.substring(7, headerAuth.length());
			try {
				if(jwtUtils.validateJwtToken(refreshToken)) {
					String newToken = jwtUtils.generateJwtToken(refreshToken);
					String newRefreshToken = jwtUtils.generateRefreshJwtToken(newToken);
					HashMap<String, String> tokens = new HashMap<>();
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
	
	@PutMapping(value = "{id}/update")
	public ResponseEntity<?> changePassword(@PathVariable("id") Long id, @RequestBody MemberUpdateDTO memberDTO) throws BizException {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(memberDTO.getMemberEmail(), memberDTO.getMemberoldPwd()));
		Member result = memberService.update(id, memberDTO);
		HashMap<String, Object> responseMap = new HashMap<>();
		responseMap.put("update", result);
		return getResponseEntity(responseMap);
	}

}
