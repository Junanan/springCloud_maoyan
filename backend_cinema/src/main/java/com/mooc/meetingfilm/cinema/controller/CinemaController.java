package com.mooc.meetingfilm.cinema.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.mooc.meetingfilm.cinema.controller.vo.CinemaSavedReqVO;
import com.mooc.meetingfilm.cinema.controller.vo.DescribeCinemasRespVO;
import com.mooc.meetingfilm.cinema.service.CinemaServiceAPI;
import com.mooc.meetingfilm.utils.common.vo.BasePageVO;
import com.mooc.meetingfilm.utils.common.vo.BaseResponseVO;
import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.cinema.controller
 * @description : 影院模块表现层
 **/
@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    @RequestMapping(value = "/cinema:add",method = RequestMethod.POST)
    public BaseResponseVO saveCinema(@RequestBody CinemaSavedReqVO cinemaSavedReqVO) throws CommonServiceException {

        // 数据验证
        cinemaSavedReqVO.checkParam();

        cinemaServiceAPI.saveCinema(cinemaSavedReqVO);

        return BaseResponseVO.success();
    }

    /*
        fallback是业务处理的保底方案，尽可能保证这个保底方案一定能成功
     */
    public BaseResponseVO fallbackMethod(BasePageVO basePageVO) throws CommonServiceException{
        /* 保底例子
            打发票， -》 没打印纸了， 换台机器或者下次再试
            -》 触发告警 -》 告知运维人员，打印发票业务挂了
         */
        // describeCinemas -》 获取影院列表 -> 在Redis中查询影院列表[redis挂了，或者数据没了] -> 获取超时

        // fallback里捕获到超时或者数据内容与分页数不一致

        // fallback就在数据库中查询真实的影院信息

        // 返回一定是成功，或者业务处理失败
        Map<String,Object> map = Maps.newHashMap();
        map.put("code","500");
        map.put("message","请求处理降级返回");
        return BaseResponseVO.success(map);
    }
    //fallbackMethod对应上面的方法
    @HystrixCommand(fallbackMethod = "fallbackMethod",
        commandProperties = {
                @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"), // 隔离方法
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "1000"), // 存活时间
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//滚动窗口中将使断路器跳闸的最小请求数量
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
        },
        threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "1"),
                @HystrixProperty(name = "maxQueueSize", value = "10"),
                @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
    },ignoreExceptions = CommonServiceException.class) // 无视异常
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO describeCinemas(BasePageVO basePageVO) throws CommonServiceException {

        IPage<DescribeCinemasRespVO> describeCinemasRespVOIPage = cinemaServiceAPI.describeCinemas(basePageVO.getNowPage(), basePageVO.getPageSize());

        if(basePageVO.getNowPage()>10000){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // TODO 调用封装的分页返回方法

        return BaseResponseVO.success();
    }

}
