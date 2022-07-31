package com.insung.lol.member.service;

import com.insung.lol.common.exception.BizException;
import com.insung.lol.member.domain.Member;
import com.insung.lol.member.repository.MemberRepository;
import com.insung.lol.member.vo.UpdateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
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

	public void updateMember(Long seq, UpdateVO updateVO) throws BizException {
		Member mem = memberRepository.findBySeq(seq);
		if (!passwordEncoder.matches(updateVO.getPw(), mem.getPw())) {
			throw new BizException("passwordError", "비밀번호가 맞지 않습니다.");
		}
		updateVO.setChangePw(passwordEncoder.encode(updateVO.getChangePw()));
		mem.updateMember(updateVO);
	}

}
