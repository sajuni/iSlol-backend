package com.insung.lol.member.domain;

public enum MemberERole {
	ROLE_USER("ROLE_USER"),
	ROLE_ADMIN("ROLE_ADMIN");

	private final String name;

	MemberERole(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
