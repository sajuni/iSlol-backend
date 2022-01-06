package com.insung.lol.auth.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String phoneNumber;
    private String name;

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getName() {
		return name;
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
