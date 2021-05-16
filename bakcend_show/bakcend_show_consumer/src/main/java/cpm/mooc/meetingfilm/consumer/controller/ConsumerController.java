package cpm.mooc.meetingfilm.consumer.controller;

import cpm.mooc.meetingfilm.consumer.service.ConsumerServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    @RequestMapping("/sayhello")
    public String sayHello(String message) {
        return consumerServiceAPI.sayHello(message);
    }
}
