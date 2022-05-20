package com.insung.lol.member.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insung.lol.common.domain.BaseTimeEntity;
import com.insung.lol.media.domain.Media;
import com.insung.lol.member.projection.MemberPrj;
import com.insung.lol.notice.domain.Notice;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "TB_MEMBER", uniqueConstraints = { @UniqueConstraint(columnNames = "MEMBER_EMAIL") })
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_seq")
	private Long memberSeq;

	@Column(name = "member_id", length = 30, nullable = false)
	private String memberId;

	@Column(name = "member_pwd", length = 100, nullable = false)
	private String memberPwd;

	@Column(name = "member_name", length = 30, nullable = false)
	private String memberName;

	@Column(name = "member_email", length = 100, nullable = false)
	private String memberEmail;

	@Column(name = "member_nick", length = 10, nullable = false)
	private String memberNick;

	@Column(name = "member_tel", length = 11, nullable = true)
	private String memberTel;

	@Column(name = "is_deleted", nullable = true, insertable = false, updatable = false)
	@JsonIgnore
	private Boolean isDeleted;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_MEM_ROLES", joinColumns = @JoinColumn(name = "member_seq"), inverseJoinColumns = @JoinColumn(name = "role_seq"))
	private Set<MemberRoles> roles = new HashSet<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Notice> notice = new ArrayList<>();

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Media> video = new ArrayList<>();

	public Member() {}

	public Member(String memberId, String memberPwd,
			String memberName, String memberNick) {
		this.memberId			= memberId;
		this.memberPwd 			= memberPwd;
		this.memberName 		= memberName;
		this.memberNick			= memberNick;
	}

	public Member(MemberPrj mem) {
		this.memberSeq			= mem.getMemberSeq();
		this.memberId			= mem.getMemberId();
		this.memberPwd 			= mem.getMemberPwd();
		this.memberName 		= mem.getMemberName();
		this.memberNick			= mem.getMemberNick();
	}

}
