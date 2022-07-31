package com.insung.lol.auth.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insung.lol.member.domain.Member;
import com.insung.lol.member.repository.MemberRepository;
@Slf4j
@Service
public class UserService {

	@Autowired
	MemberRepository memberRepository;

	public Member getUserInfo(String memberId) throws UsernameNotFoundException {
		System.out.println("테스트중");
		log.info("테스트중: {}", memberId);
		Member member = memberRepository.findById(memberId)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + memberId));

		return member;
	}
}
