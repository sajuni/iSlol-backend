package com.insung.lol.member.domain;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.insung.lol.notice.domain.Notice;
import com.insung.lol.video.domain.Video;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_MEMBER", uniqueConstraints = { @UniqueConstraint(columnNames = "MEMBER_EMAIL") })
public class Member implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBER_SEQ")
	private Long memberSeq;

	@Column(name = "MEMBER_EMAIL", length = 50, nullable = false)
	private String memberEmail;

	@Column(name = "MEMBER_PWD", length = 100, nullable = false)
	private String memberPwd;

	@Column(name = "MEMBER_Name", length = 10, nullable = false)
	private String memberName;

	@Column(name = "MEMBER_ADDR", length = 255, nullable = true)
	private String memberAddr;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_MEM_ROLES", joinColumns = @JoinColumn(name = "MEMBER_SEQ"), inverseJoinColumns = @JoinColumn(name = "ROLE_SEQ"))
	private Set<MemberRoles> roles = new HashSet<MemberRoles>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Notice> notice = new ArrayList<Notice>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Video> video = new ArrayList<Video>();

	public Member() {}

	public Member(String memberEmail, String memberPwd,
			String memberName, String memberAddr) {
		this.memberEmail		= memberEmail;
		this.memberPwd 			= memberPwd;
		this.memberName 		= memberName;
		this.memberAddr			= memberAddr;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
