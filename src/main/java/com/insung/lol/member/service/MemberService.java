package com.insung.lol.member.service;

import com.insung.lol.member.domain.Member;
import com.insung.lol.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {

 	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public Optional<Member> findById(String id) {
		return memberRepository.findById(id);
	}

	public boolean existsByMemberId(String id) {
		return memberRepository.existsById(id);
	}

	public Member signUpMember(Member mem) {
		mem.setPw(passwordEncoder.encode(mem.getPw()));
		return memberRepository.save(mem);
	}

}
