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

	public Optional<Member> findMemberById(String memberId) {
		return memberRepository.findByMemberId(memberId);
	}

	public boolean existsByMemberId(String memberId) {
		return memberRepository.existsByMemberId(memberId);
	}

	public Member signUpMember(Member mem) {
		mem.setMemberPw(passwordEncoder.encode(mem.getMemberPw()));
		return memberRepository.save(mem);
	}

}
