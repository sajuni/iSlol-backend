package com.insung.lol.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insung.lol.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
