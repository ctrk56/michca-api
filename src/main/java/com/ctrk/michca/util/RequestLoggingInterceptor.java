package com.ctrk.michca.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class RequestLoggingInterceptor implements ClientHttpRequestInterceptor {

    private final static Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        
        request.getHeaders().setCacheControl("no-cache");
        request.getHeaders().add("x-apikey", "59f88e22741783cb062d807f");
        request.getHeaders().setContentType(MediaType.APPLICATION_JSON); 
        
        ClientHttpResponse response = execution.execute(request, body);
        
        log.info("request method: {}, request URI: {}, request headers: {}, request body: {}, response status code: {}, response headers: {}, response body: {}",
            request.getMethod(),
            request.getURI(),
            request.getHeaders(),
            new String(body, Charset.forName("UTF-8")),
            response.getStatusCode(),
            response.getHeaders(),
            response.getBody());
        
        return response;
    }


}
