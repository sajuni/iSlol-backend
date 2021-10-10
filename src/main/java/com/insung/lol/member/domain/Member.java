package com.insung.lol.member.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_MEMBER", uniqueConstraints = { @UniqueConstraint(columnNames = "MEMBER_EMAIL") })
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_SEQ")
	private long MemberSeq;
	
	@Column(name = "MEMBER_EMAIL", length = 50, nullable = false)
	private String MemberEmail;

	@Column(name = "MEMBER_PWD", length = 100, nullable = false)
	private String MemberPwd;

	@Column(name = "MEMBER_Name", length = 10, nullable = false)
	private String MemberName;
	
	@Column(name = "MEMBER_ADDR", length = 255, nullable = false)
	private String MemberAddr;

	@Builder
	public Member(long memberSeq, String memberEmail, String memberPwd, String memberName, String memberAddr) {
		this.MemberSeq		= memberSeq;
		this.MemberEmail	= memberEmail;
		this.MemberPwd 		= memberPwd;
		this.MemberName 	= memberName;
		this.MemberAddr		= memberAddr;
	}

	
}
