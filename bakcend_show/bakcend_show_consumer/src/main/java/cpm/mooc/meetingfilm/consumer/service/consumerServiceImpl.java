package cpm.mooc.meetingfilm.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author yangjunnan
 * @date 2021/5/16
 */
@Service
public class consumerServiceImpl implements ConsumerServiceAPI {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient eurekaClient;
    @Override
    public String sayHello(String message) {
        ServiceInstance choose = eurekaClient.choose("hello-service-provider");

        String hostname = choose.getHost();
        int port = choose.getPort();
        String uri = "/provider/sayhello?message="+message;
        String url = "http://"+hostname+":"+port+uri;


        // invoker provider test
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
}
