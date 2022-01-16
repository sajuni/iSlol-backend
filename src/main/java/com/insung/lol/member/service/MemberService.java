package com.insung.lol.member.service;

import com.insung.lol.common.exception.BizException;
import com.insung.lol.member.domain.Member;
import com.insung.lol.member.dto.MemberUpdateDTO;
import com.insung.lol.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
		return memberRepository.findByMemberEmail(email);
	}

	public Member update(Long id, MemberUpdateDTO memberDTO) throws BizException {

		Optional<Member> mem = memberRepository.findById(id);

		mem.ifPresent(v -> {
			v.setMemberPwd(passwordEncoder.encode(memberDTO.getMemberPwd()));
			v.setMemberAddr(memberDTO.getMemberAddr());
			v.setMemberNick(memberDTO.getMemberNick());
		});

		return memberRepository.save(mem.get());
	}


	public void save(Member member) {
		memberRepository.save(member);
	}

	public boolean existsByMemberEmail(String email) {
		return memberRepository.existsByMemberEmail(email);
	}

}
