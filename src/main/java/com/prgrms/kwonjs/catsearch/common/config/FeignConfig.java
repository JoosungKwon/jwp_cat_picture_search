package com.prgrms.kwonjs.catsearch.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.RequestInterceptor;

@Configuration
@EnableFeignClients("com.prgrms.kwonjs.catsearch")
class FeignConfig {

	@Value("${cat-api.key}")
	private String API_KEY;

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> requestTemplate.header("x-api-key", API_KEY);
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
