package com.insung.lol.auth.util;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.insung.lol.member.service.MemberService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.openobject.goolbi.lpogbboserver.common.auth.security.services.BackOfficeUserDetailsImpl;

@Service
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 4277374909431585293L;
	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@Value("${jwt.rjwtExpirationMs}")
	private int rjwtExpirationMs;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * generateJwtToken <br/>
	 * jwt 토큰 생성
	 * 
	 */
	public String generateJwtToken(Authentication authentication) {

		BackOfficeUserDetailsImpl userPrincipal = (BackOfficeUserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

}
