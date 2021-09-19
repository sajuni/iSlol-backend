package com.insung.lol.user.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import lombok.Data;

@Data
@Entity
@Table(name = "TB_USER", uniqueConstraints = { @UniqueConstraint(columnNames = "USER_ID") })
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_SEQ")
	private Long userSeq;
	
	@Column(name = "USER_ID", length = 20, nullable = false)
	private String userId;

	@Column(name = "USER_PW", length = 255, nullable = false)
	private String userPw;

	@Column(name = "USER_NM", length = 10, nullable = false)
	private String userNm;

	@Column(name = "USER_EMAIL_ADDR", length = 50, nullable = false)
	private String userEmailAddr;
	
}
