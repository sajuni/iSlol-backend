package com.insung.lol.auth.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.insung.lol.auth.security.service.UserDetailsImpl;
import com.insung.lol.auth.security.service.UserService;
import com.insung.lol.member.domain.Member;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${jwt.expired.jwtSecret}")
	private String jwtSecret;

	@Value("${jwt.expired.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	@Value("${jwt.expired.rjwtExpirationMs}")
	private int rjwtExpirationMs;
	
	@Autowired
	private UserService userService;

	/**
	 * generateJwtToken <br/>
	 * jwt 토큰 생성
	 * 
	 */
	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	/**
	 * generateJwtToken <br/>
	 * jwt 리프레시 토큰 생성
	 * 
	 */
	public String generateJwtToken(String token) {
		String username = getUserNameFromJwtToken(token);
		return Jwts.builder().setSubject((username)).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	/**
	 * getUserNameFromJwtToken <br/>
	 * jwt토큰으로 userId 조회
	 * 
	 */
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * validateJwtToken <br/>
	 * jwt토큰 유효성 체크
	 * 
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
	
	/**
	 * validateJwtToken <br/>
	 * jwt토큰으로 리프레시토큰 생성
	 * 
	 */
	public String generateRefreshJwtToken(String token) {
		String username = getUserNameFromJwtToken(token);

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + rjwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	/**
	 * getTokenUserInfoByHttpRequest <br/>
	 * http request의 헤더의 jwt토큰으로 사용자정보 조회
	 * 
	 */
	public Member getTokenUserInfoByHttpRequest(HttpServletRequest request) {
		String jwt = getJwtToken(request);
		if (jwt != null && validateJwtToken(jwt)) {
			String userId = getUserNameFromJwtToken(jwt);
			Member userInfo = userService.getUserInfo(userId);
			
			return userInfo;
		}
		return null;
	}
	
	/**
	 * getUserIdByHttpRequest <br/>
	 * http request의 헤더의 jwt토큰으로 사용자ID 조회
	 * 
	 */
	public String getUserIdByHttpRequest(HttpServletRequest request) {
		return getUserNameFromJwtToken(getJwtToken(request));
	}
	
	/**
	 * getJwtToken <br/>
	 * http request의 헤더의 jwt토큰조회
	 * 
	 */
	public String getJwtToken(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}
	
	/**
	 * getLoginUserEntity <br/>
	 * ContextHolder 에서 사용자정보 조회
	 * 
	 */
	public UserDetailsImpl getLoginUserEntity() {
		return (UserDetailsImpl)RequestContextHolder.getRequestAttributes().getAttribute("loginUserInfo", RequestAttributes.SCOPE_SESSION);
	}
}

