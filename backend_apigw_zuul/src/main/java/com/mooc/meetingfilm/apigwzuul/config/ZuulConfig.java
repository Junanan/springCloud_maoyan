package com.mooc.meetingfilm.apigwzuul.config;

import com.mooc.meetingfilm.apigwzuul.filters.JWTFilter;
import com.mooc.meetingfilm.apigwzuul.filters.MyFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangjunnan
 * @date 2021/6/8
 */
/// 不写这个 直接在MyFilter 加@Component 或者 @service
@Configuration

public class ZuulConfig {

    @Bean
    public MyFilter initMyFilter() {
        return new MyFilter();
    }
    @Bean
    public JWTFilter initJWTFilter() {
        return new JWTFilter();
    }
}
