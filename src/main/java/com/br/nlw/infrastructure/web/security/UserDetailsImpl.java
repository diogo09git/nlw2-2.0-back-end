package com.br.nlw.infrastructure.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.nlw.domain.user.AppUser;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {
	
	private String email;
	private String name;
	private String password;
	
	public UserDetailsImpl(AppUser appUser) {
		this.email = appUser.getEmail();
		this.name = appUser.getName();
		this.password = appUser.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.NO_AUTHORITIES;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}
	
	public String getEmail() {
		return email;
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
