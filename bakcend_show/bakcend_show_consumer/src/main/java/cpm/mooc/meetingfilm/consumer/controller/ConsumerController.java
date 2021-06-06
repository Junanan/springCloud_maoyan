package cpm.mooc.meetingfilm.consumer.controller;

import cpm.mooc.meetingfilm.consumer.feign.ProviderApi;
import cpm.mooc.meetingfilm.consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author yangjunnan
 * @date 2021/5/16
 */
@RestController
public class ConsumerController {
    @Autowired
    private ConsumerServiceAPI consumerServiceAPI;
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private ProviderApi providerApi;


    @RequestMapping("/sayhello")
    public String sayHello(String message) {
        return consumerServiceAPI.sayHello(message);
    }
    @RequestMapping("/sayhello/feign")
    public String sayHello2(String message) {
        System.out.println("feign message:"+message);
        return providerApi.invokerProviderController(message);
    }
}
