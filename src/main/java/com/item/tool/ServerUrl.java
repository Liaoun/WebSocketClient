package com.item.tool;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServerUrl {

    //    public static final String supplier="API-GETEWAY/service";
    //设置后端服务名 supplier（供应站）
    public static final  String URL="http://WEBSOCKET-SERVICE";
    @LoadBalanced
    @Bean
    public RestTemplate get_template(){
        return new RestTemplate();
    }

    /**
     * 忽略熔断的提供方
     * @return
     */
    @Bean
    public IRule get_irule(){
        return new RetryRule();
    }



}

