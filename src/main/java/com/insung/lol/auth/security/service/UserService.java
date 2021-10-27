package com.insung.lol.auth.security.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insung.lol.member.domain.Member;
import com.insung.lol.member.repository.MemberRepository;
@Transactional
@Service
public class UserService {

	@Autowired
	MemberRepository memberRepository;
	
	public Member getUserInfo(String userId) throws UsernameNotFoundException {
		Member member = memberRepository.findByMemberEmail(userId)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + userId));

		return member;
	}
}
