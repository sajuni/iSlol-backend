package com.insung.lol.member.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.insung.lol.common.exception.EmailDuplicateException;
import com.insung.lol.common.exception.ErrorCode;
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
	
	public void save(Member member) {
		Optional<Member> aleadyMember = memberRepository.findByMemberEmail(member.getMemberEmail());
		if (aleadyMember.isPresent()) {
			throw new EmailDuplicateException("이메일이 중복되었습니다.", ErrorCode.EMAIL_DUPLICATION);
		}
		member.setMemberPwd(passwordEncoder.encode(member.getMemberPwd()));
		memberRepository.save(member);
	}
	
	
	public Optional<Member> findMemberByEmail(String email) {
		Optional<Member> aleadyMember = memberRepository.findByMemberEmail(email);
		return aleadyMember;
	}
}
