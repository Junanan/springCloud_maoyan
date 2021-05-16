package provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunnan
 * @date 2021/5/16
 */
@Slf4j
@RestController
public class ProviderController {

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/provider/sayhello", method = RequestMethod.GET)
    public String providerSayHello(String message){

        log.warn("provder sayhello port:{}, message:{}",port,message);

        return "Provider sayhello port:"+port+" , message:"+message;
    }
}
