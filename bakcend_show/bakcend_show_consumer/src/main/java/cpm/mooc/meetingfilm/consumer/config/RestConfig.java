package cpm.mooc.meetingfilm.consumer.config;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.crypto.interfaces.PBEKey;

/**
 * @author yangjunnan
 * @date 2021/5/16
 */
@Configuration
public class RestConfig {
    // 这个注解告诉spring cloud 创建一个支持 Ribbon 的 RestTemplate
//    @LoadBalanced
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    //Ribbon 负载均衡算法 -> 随机规则
    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
    //参数1：是不是安全的链接，也就是http和http是的区别。
    //参数2：是要ping的url
    @Bean
    public IPing iPing(){
        return new PingUrl(false,"/asd");
    }
}
