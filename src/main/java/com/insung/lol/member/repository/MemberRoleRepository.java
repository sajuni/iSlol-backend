package com.insung.lol.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insung.lol.member.domain.MemberERole;
import com.insung.lol.member.domain.MemberRoles;

/** 
* @packageName 		: com.insung.lol.member.repository 
* @fileName 		: MemberRoleRepository.java 
* @author 			: Seung Hyo 
* @date 			: 2021.10.17 
* @description 		: 사용자 권한조회
* =========================================================== 
* DATE 					   AUTHOR 			NOTE * 
----------------------------------------------------------- 
* 2021.10.17 			   Seung Hyo 	    최초 생성 
*/
public interface MemberRoleRepository extends JpaRepository<MemberRoles, Long>{

	/**
	 * @methodName 	: findByRoleName
	 * @author 		: Seung Hyo
	 * @date 		: 2021.10.17 
	 * @description : 권한명으로 권한정보 조회
	 * @param roleName
	 * @return
	 */
	Optional<MemberRoles> findByRoleName(MemberERole roleName);
	
}
