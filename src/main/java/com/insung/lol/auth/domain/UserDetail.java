package com.insung.lol.auth.domain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insung.lol.member.domain.Member;

public class UserDetail implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long memberSeq;
	private String memberId;
	private String memberName;
	private String memberEmail;
	private String memberNick;
	private String memberTel;
	@JsonIgnore
	private String memberPw;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetail(Long memberSeq, String memberId, String memberName, String memberEmail, String memberNick, String memberTel, String memberPw, Collection<? extends GrantedAuthority> authorities) {
		this.memberSeq = memberSeq;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberNick = memberNick;
		this.memberTel = memberTel;
		this.memberPw = memberPw;
		this.authorities = authorities;
	}

	public static UserDetail build(Member member) {
		List<GrantedAuthority> authorities = member.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());

		return new UserDetail(member.getMemberSeq(), member.getMemberId(), member.getMemberName()
				, member.getMemberEmail(), member.getMemberNick(), member.getMemberTel(), member.getMemberPw(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getMemberSeq() {
		return memberSeq;
	}

	public void setUserSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}

	@Override
	public String getPassword() {
		return memberPw;
	}

	@Override
	public String getUsername() {
		return memberId;
	}

	public String getMemberNick() { return memberNick; }

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
