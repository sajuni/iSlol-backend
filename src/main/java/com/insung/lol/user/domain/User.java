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
@Table(name = "TB_USER", uniqueConstraints = { @UniqueConstraint(columnNames = "USER_EMAIL") })
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_SEQ")
	private long userSeq;
	
	@Column(name = "USER_EMAIL", length = 50, nullable = false)
	private String userEmail;

	@Column(name = "USER_PWD", length = 100, nullable = false)
	private String userPwd;

	@Column(name = "USER_Name", length = 10, nullable = false)
	private String userName;
	
	@Column(name = "USER_ADDR", length = 255, nullable = false)
	private String userAddr;

}
