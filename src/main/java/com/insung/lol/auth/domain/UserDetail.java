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

	private Long seq;
	private String id;
	private String name;
	private String email;
	private String nick;
	private String mobileNum;
	@JsonIgnore
	private String pw;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetail(Long seq, String id, String name, String email, String nick, String mobileNum, String pw, Collection<? extends GrantedAuthority> authorities) {
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.email = email;
		this.nick = nick;
		this.mobileNum = mobileNum;
		this.pw = pw;
		this.authorities = authorities;
	}

	public static UserDetail build(Member member) {
		List<GrantedAuthority> authorities = member.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());

		return new UserDetail(member.getSeq(), member.getId(), member.getName()
				, member.getEmail(), member.getNick(), member.getMobileNum(), member.getPw(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@Override
	public String getPassword() {
		return pw;
	}

	@Override
	public String getUsername() {
		return id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
