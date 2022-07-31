package com.insung.lol.member.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insung.lol.common.domain.BaseTimeEntity;
import com.insung.lol.member.vo.SignUpVO;
import com.insung.lol.member.vo.UpdateVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TB_MEMBER", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Member extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

	@Column(name = "id", length = 30, nullable = false)
	private String id;

	@Column(name = "pw", length = 100, nullable = false)
	private String pw;

	@Column(name = "name", length = 30, nullable = false)
	private String name;

	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@Column(name = "nick", length = 10, nullable = false)
	private String nick;

	@Column(name = "tel", length = 11)
	private String mobileNum;

	@Column(name = "is_deleted", insertable = false, updatable = false, columnDefinition = "boolean default false")
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
		this.id = signUpVO.getId();
		this.pw = signUpVO.getPw();
		this.name = signUpVO.getName();
		this.email = signUpVO.getEmail();
		this.nick = signUpVO.getNick();
		this.mobileNum = signUpVO.getMobileNum();
	}

	public void updateMember(UpdateVO updateVO) {
		this.email = updateVO.getEmail();
		this.nick = updateVO.getNick();
		this.mobileNum = updateVO.getMobileNum();
		this.pw = updateVO.getChangePw();
	}

}
