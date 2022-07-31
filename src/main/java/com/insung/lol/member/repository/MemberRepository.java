package com.insung.lol.member.repository;

import com.insung.lol.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(String id);
    boolean existsById(String id);
    Member findBySeq(Long seq);
}
