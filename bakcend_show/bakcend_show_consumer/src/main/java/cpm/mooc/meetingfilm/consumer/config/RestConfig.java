package cpm.mooc.meetingfilm.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangjunnan
 * @date 2021/5/16
 */
@Configuration
public class RestConfig {
    // 这个注解告诉spring cloud 创建一个支持 Ribbon 的 RestTemplate
//    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
