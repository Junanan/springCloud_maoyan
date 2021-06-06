package cpm.mooc.meetingfilm.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangjunnan
 * @date 2021/6/5
 */
//url 指定服务 name 如果为provider的服务 就不需要  相当于集合了ribbon
    //FeignHelloConf 自定义配置 不能和consumer在同一路径
@FeignClient(name = "hello-service-provider",
        primary = true,
        path = "/provider",
        fallbackFactory = FallbackFactory.class
//        fallback = ProviderFallbackAPIImpl.class
//        configuration = FeignHelloConf.class,
//        url = "http://localhost:7101"
)
public interface ProviderApi {
    @RequestMapping(value = "/provider/sayhello", method = RequestMethod.GET)
    String invokerProviderController(@RequestParam("message") String message);
}
