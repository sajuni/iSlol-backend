package com.insung.lol.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insung.lol.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	/**
	 * @methodName 	: findByMemberEmail
	 * @author 		: Seung Hyo
	 * @date 		: 2021.10.17 
	 * @description : 사용자 Email로 사용자 정보 조회
	 * @param email
	 * @return
	 */
	Optional<Member> findByMemberEmail(String memberEmail);
	
	/**
	 * @methodName 	: existsByUserId
	 * @author 		: Seung Hyo
	 * @date 		: 2021.10.17 
	 * @description : 사용자 Email 존재여부 조회
	 * @param userId
	 * @return
	 */
	boolean existsByMemberEmail(String memberEmail);
}
