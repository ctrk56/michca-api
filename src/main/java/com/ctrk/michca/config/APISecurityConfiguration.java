package com.ctrk.michca.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class APISecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("mobile").password("iLoveCricket").roles("USER");
	}

//	@Bean
//	UserDetailsService getUser() {
//		return new UserDetailsService() {
//			@Override
//			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//				UserDetails user = Temporary.USERS.get(username);
//				if (user != null) {
//					return user;
//				} else {
//					throw new UsernameNotFoundException("could not find the user '" + username + "'");
//				}
//			}
//
//		};
//	}
}

