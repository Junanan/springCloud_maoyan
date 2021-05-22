package com.mooc.meetingfilm.hall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangjunnan
 * @date 2021/5/22
 */
@Configuration
public class RestConf {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
