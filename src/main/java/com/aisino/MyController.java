package com.aisino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class MyController {

	@Bean
	@MyLoadBalanced
	RestTemplate tplA(){
	
		return new RestTemplate();
	}
	
//	@Bean
//	@MyLoadBalanced
//	RestTemplate tplB(){
//		
//		return new RestTemplate();
//	}
	
	
	@RequestMapping(value="/call", method=RequestMethod.GET)
	public String call(){
		
		RestTemplate tpl = tplA();
		return tpl.getForObject("http://hello-service/call", String.class);
	}
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String hello(){
		
		return "hello word";
	}
}
