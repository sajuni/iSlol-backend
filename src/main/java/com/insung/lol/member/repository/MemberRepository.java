package com.insung.lol.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insung.lol.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	public Optional<Member> findByMemberEmail(String email);
}
