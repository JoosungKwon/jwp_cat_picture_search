package com.prgrms.kwonjs.catsearch.common.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.prgrms.kwonjs.catsearch")
class FeignConfig {
}
