package com.mooc.meetingfilm.showhystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import lombok.Data;

/**
 * @program : com.imooc.hystrix.show.command
 * @description :
 **/
@Data
public class CommandDemo extends HystrixCommand<String> {

    private String name;

    public CommandDemo(String name){
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandHelloWorld"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter()
                            .withRequestCacheEnabled(false) // 请求缓存开关)
                                // 切换线程池隔离还是信号量隔离
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                        .withExecutionIsolationSemaphoreMaxConcurrentRequests(2)
//                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        // .withCircuitBreakerForceOpen(true) // 强制开启熔断器
                        .withCircuitBreakerRequestVolumeThreshold(2) // 单位时间内的请求阈值
                        .withCircuitBreakerErrorThresholdPercentage(50) // 当满足请求阈值时，超过50%则开启熔断
        ).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("MyThreadPool")) // 配置ThreadPool
//         .andThreadPoolPropertiesDefaults(
//                 HystrixThreadPoolProperties.defaultSetter()
//                    .withCoreSize(2)
//                    .withMaximumSize(3).withAllowMaximumSizeToDivergeFromCoreSize(true)
//                    .withMaxQueueSize(2)
//         )
        );

        this.name = name;
    }


    /**
    * @Description: 查询0个影片信息
     * 降级（备胎）
    */
    @Override
    protected String getFallback() {
        String result = "Fallback name : "+ name;

        System.err.println(result+" , currentThread-"+Thread.currentThread().getName());

        return result;
    }

    /**
    * @Description:  影厅新增查询影片信息
    */
    // 单次请求调用的业务方法
    @Override
    protected String run() throws Exception {
        String result = "CommandHelloWorld name : "+ name;

//        Thread.sleep(800l);

        if(name.startsWith("jiangzh")){
            int i = 6/0;
        }

        System.err.println(result+" , currentThread-"+Thread.currentThread().getName());

        return result;
    }


    @Override
    protected String getCacheKey() {
        return String.valueOf(name);
    }
}
