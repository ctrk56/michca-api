package com.ctrk.michca.util;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.ctrk.michca.interceptors.RestDBInterceptor;

public final class RestTemplateUtil {
	
	final static boolean isProxyEnabled = false;
	
	public static RestTemplate getRESTDBIORestTemplate() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		
		if(isProxyEnabled) {
			Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("wwwproxy03.mi.us.comerica.net", 8080));
			requestFactory.setProxy(proxy);
		}

		final RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> ll = new ArrayList<ClientHttpRequestInterceptor>();
		ll.add(new RestDBInterceptor());
		restTemplate.setInterceptors(ll);
		
		return restTemplate;
	}

	public static RestTemplate getBasicRestTemplate() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		
		if(isProxyEnabled) {
			Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("wwwproxy03.mi.us.comerica.net", 8080));
			requestFactory.setProxy(proxy);
		}
		
		return new RestTemplate(requestFactory);
	}

}
