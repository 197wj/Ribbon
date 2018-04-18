package com.aisino;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class MyInterceptor implements ClientHttpRequestInterceptor {

	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {

		System.out.println("####这是自定义拦截器####");
		System.out.println("***********旧的 URI：" + request.getURI());
		
		HttpRequest newRequest = new MyRequest(request);
		System.out.println("&&&&&&&&&&&&新的 URI：" + newRequest.getURI());
		
		return execution.execute(newRequest, body);
	}

}
