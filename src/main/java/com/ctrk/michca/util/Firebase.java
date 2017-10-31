package com.ctrk.michca.util;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ctrk.michca.adk.User;
import com.ctrk.michca.adk.UserWrapper;

@Component
public class Firebase {

	private RestTemplate restTemplate = null;

	Firebase() {
//		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//		Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("wwwproxy03.mi.us.comerica.net", 8080));
//		requestFactory.setProxy(proxy);
//
//		restTemplate = new RestTemplate(requestFactory);
//		
//		try {
//			SSLUtil.turnOffSslChecking();
//		} catch (KeyManagementException e) {
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
		restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		restTemplate.setInterceptors(Collections.singletonList(new RequestLoggingInterceptor()));
	}

	public User getData(String url) {
		return restTemplate.getForObject(url, User.class);
	}

	public void createUser() {
		User user = new User();
		user.setId(1L);
		user.setName("name2");
		user.setPassword("password2");
		user.setRoles("roles2");
		UserWrapper userWrapper = new UserWrapper(user, null);
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", "application/json");
		headers.set("x-apikey", "59f88e22741783cb062d807f");
		headers.set("cache-control", "no-cache");
		HttpEntity<User> request = new HttpEntity<>(user, headers);
		restTemplate.postForObject("https://michca-114b.restdb.io/rest/michca-2017", request, User.class);
	}
}
