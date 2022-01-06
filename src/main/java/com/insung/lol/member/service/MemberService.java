package com.insung.lol.member.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insung.lol.member.domain.Member;
import com.insung.lol.member.repository.MemberRepository;

@Service
public class MemberService {

	MemberRepository memberRepository;
	PasswordEncoder passwordEncoder;

	@Autowired
	public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
		this.memberRepository 	= memberRepository;
		this.passwordEncoder 	= passwordEncoder;
	}


	public Optional<Member> findMemberByEmail(String email) {
		Optional<Member> aleadyMember = memberRepository.findByMemberEmail(email);
		return aleadyMember;
	}
}
