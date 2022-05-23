package com.insung.lol.member.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "TB_MEMBER_ROLES")
public class MemberRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_seq")
	private Long roleSeq;

	@Enumerated(EnumType.STRING)
	@Column(name = "role_name", nullable = false, columnDefinition = "enum('ROLE_USER', 'ROLE_ADMIN')")
	private MemberERole roleName;

	public MemberRoles() {
	}

	public MemberRoles(Long roleSeq, MemberERole roleName) {
		this.roleSeq = roleSeq;
		this.roleName = roleName;
	}

	public MemberRoles roleSeq(long roleSeq) {
		this.roleSeq = roleSeq;
		return this;
	}

	public MemberRoles roleName(MemberERole roleName) {
		this.roleName = roleName;
		return this;
	}

}
