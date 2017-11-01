package com.ctrk.michca.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ctrk.michca.adk.User;
import com.ctrk.michca.adk.UserWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestDBBroker {
	
    private final static Logger log = LoggerFactory.getLogger(RestDBBroker.class);

	private static final String URL = "https://michca-114b.restdb.io/rest/michcausers";
	private RestTemplate restTemplate = null;

	RestDBBroker() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		
		restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> ll = new ArrayList<ClientHttpRequestInterceptor>();
		ll.add(new RequestLoggingInterceptor());
		restTemplate.setInterceptors(ll);
		
		try {
			SSLUtil.turnOffSslChecking();
		} catch (Exception e) {
			log.error("Error turning off SSL checking", e.getMessage());
		}
	}

	public List<UserWrapper> getUsers() {
		List<UserWrapper> result = restTemplate.getForObject(URL, List.class);
		return new ObjectMapper().convertValue(result, new TypeReference<List<UserWrapper>>() {});
	}

	public void createUser() {
		User user = new User();
		user.setId(1L);
		user.setName("name2");
		user.setPassword("password2");
		user.setRoles("roles2");
		UserWrapper userWrapper = new UserWrapper(user, null);

		HttpEntity<UserWrapper> request = new HttpEntity<UserWrapper>(userWrapper);
		restTemplate.postForObject(URL, request, UserWrapper.class);
	}
}
