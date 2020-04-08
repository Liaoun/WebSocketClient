package com.item.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@FeignClient(value = "SERVICE")
public interface SystemController {
}
