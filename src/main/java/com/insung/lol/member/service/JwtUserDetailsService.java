package com.insung.lol.member.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.insung.lol.auth.domain.UserDetail;
import com.insung.lol.member.domain.Member;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberService memberService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		 Member user = memberService.findMemberById(userId)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + userId));

		 RequestContextHolder.getRequestAttributes().setAttribute("loginUserInfo", user, RequestAttributes.SCOPE_SESSION);
		 
		 return UserDetail.build(user);
	}

}
