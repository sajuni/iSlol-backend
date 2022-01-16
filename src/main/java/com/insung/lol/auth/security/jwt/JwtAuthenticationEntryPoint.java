package com.insung.lol.auth.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint  implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 6377514362070202805L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error("Unauthorized error: {}", authException.getMessage());
		if (authException.getMessage().equals("Bad credentials")) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ID/PW를 확인해 주세요.");
		} else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}
	}
}
