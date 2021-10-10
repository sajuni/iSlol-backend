package com.insung.lol.auth.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long userSeq;
	private String userEmail;
	private String userName;
	
	@JsonIgnore
	private String userPwd;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	private String roleCode;
	
	public UserDetailsImpl(Long userSeq, String userEmail, String userName, String userPwd,
			Collection<? extends GrantedAuthority> authorities) {
		this.userSeq = userSeq;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPwd = userPwd;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}
