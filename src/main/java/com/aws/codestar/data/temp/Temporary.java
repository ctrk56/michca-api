package com.aws.codestar.data.temp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class Temporary {

	public static final Map<String, User> USERS;
	public static final Map<String, Boolean> USERS_LOGGED_IN;
	static {
		//User(String username, String password, boolean enabled,
		//boolean accountNonExpired, boolean credentialsNonExpired,
		//boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
		USERS = new HashMap<String, User>();
		USERS_LOGGED_IN = new HashMap<String, Boolean>();
		USERS.put("mobile", new User("mobile", "iLoveCricket", true, true, true, true, AuthorityUtils.createAuthorityList("USER")));
		USERS.put("web", new User("web", "iLikeCricket", true, true, true, true, AuthorityUtils.createAuthorityList("USER")));
		USERS.put("ctrk", new User("ctrk", "iAmKing", true, true, true, true, AuthorityUtils.createAuthorityList("USER")));
	}
	
}
