package com.insung.lol.member.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insung.lol.common.domain.BaseTimeEntity;
import com.insung.lol.member.vo.SignUpVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_MEMBER", uniqueConstraints = { @UniqueConstraint(columnNames = "member_id") })
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_seq")
	private Long memberSeq;

	@Column(name = "member_id", length = 30, nullable = false)
	private String memberId;

	@Column(name = "member_pw", length = 100, nullable = false)
	private String memberPw;

	@Column(name = "member_name", length = 30, nullable = false)
	private String memberName;

	@Column(name = "member_email", length = 100, nullable = false)
	private String memberEmail;

	@Column(name = "member_nick", length = 10, nullable = false)
	private String memberNick;

	@Column(name = "member_tel", length = 11, nullable = true)
	private String memberTel;

	@Column(name = "is_deleted", nullable = true, insertable = false, updatable = false, columnDefinition = "boolean default false")
	@JsonIgnore
	private Boolean isDeleted;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_MEM_ROLES", joinColumns = @JoinColumn(name = "member_seq"), inverseJoinColumns = @JoinColumn(name = "role_seq"))
	private Set<MemberRoles> roles = new HashSet<>();

	@PrePersist
	public void prePersist() {
		Set<MemberRoles> memberRoles = new HashSet<>();
		MemberRoles roles = new MemberRoles();
		roles.setRoleSeq(1L);
		roles.setRoleName(MemberERole.ROLE_USER);
		memberRoles.add(roles);
		this.roles = memberRoles;
	}

	public Member(SignUpVO signUpVO) {
		this.memberId = signUpVO.getMemberId();
		this.memberPw = signUpVO.getMemberPw();
		this.memberName = signUpVO.getMemberName();
		this.memberEmail = signUpVO.getMemberEmail();
		this.memberNick = signUpVO.getMemberNick();
		this.memberTel = signUpVO.getMemberTel();
	}

}
