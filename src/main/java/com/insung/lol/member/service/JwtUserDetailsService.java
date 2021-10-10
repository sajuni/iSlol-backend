package com.insung.lol.member.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insung.lol.member.domain.Member;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<Member> userOptional = memberService.findMemberByEmail(username);

		 Member member = userOptional.orElseThrow(()->new UsernameNotFoundException("user name not found!"));
	        return new org.springframework.security.core.userdetails.User(member.getMemberEmail(),member.getMemberPwd(),new ArrayList<>());
	}

}
