package com.insung.lol.auth.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insung.lol.member.domain.Member;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long userSeq;
	private String userEmail;
	private String userRealName;
	private String addr;

	@JsonIgnore
	private String userPwd;

	private Collection<? extends GrantedAuthority> authorities;

	private String roleCode;

	public UserDetailsImpl(Long userSeq, String userEmail, String userRealName, String userPwd, String addr, Collection<? extends GrantedAuthority> authorities) {
		this.userSeq = userSeq;
		this.userEmail = userEmail;
		this.userRealName = userRealName;
		this.userPwd = userPwd;
		this.addr = addr;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Member member) {
		List<GrantedAuthority> authorities = member.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());

		return new UserDetailsImpl(member.getMemberSeq(), member.getMemberEmail(), member.getMemberName()
				, member.getMemberPwd(), member.getMemberAddr(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	@Override
	public String getPassword() {
		return userPwd;
	}

	@Override
	public String getUsername() {
		return userEmail;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddr() {
		return addr;
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
