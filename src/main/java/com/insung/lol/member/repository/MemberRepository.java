package com.insung.lol.member.repository;

import com.insung.lol.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	/**
	 * @methodName 	: findByMemberEmail
	 * @author 		: Seung Hyo
	 * @date 		: 2021.10.17 
	 * @description : 사용자 Email 사용자 정보 조회
	 * @param memberEmail
	 * @return Member
	 */
	Optional<Member> findByMemberEmail(String memberEmail);
	
	/**
	 * @methodName 	: existsByUserId
	 * @author 		: Seung Hyo
	 * @date 		: 2021.10.17 
	 * @description : 사용자 Email 존재여부 조회
	 * @param memberEmail
	 * @return MemberDTOP
	 */
	boolean existsByMemberEmail(String memberEmail);


}
