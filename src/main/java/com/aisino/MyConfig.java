package com.aisino;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

	
	// 需要使用 Autowired 注解注入所有被 @MyLoadBalanced 定义的 RestTemplate 配置 ,因为使用了 @Configuration
	// (required=false) 非必须的， 有没有都可以注入，正常运行
	@Autowired(required=false)
	@MyLoadBalanced
	private List<RestTemplate> tpls = Collections.emptyList();

	@Bean
	public SmartInitializingSingleton lbInitializing() {

		return new SmartInitializingSingleton() {

			public void afterSingletonsInstantiated() {

//				System.out.println(tpls.size());
				
				for (RestTemplate restTemplate : tpls) {
					
					List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
					interceptors.add(new MyInterceptor());
					restTemplate.setInterceptors(interceptors);
				}
			}
		};
	}
}
