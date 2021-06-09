package com.mooc.meetingfilm.apigwzuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.compiler.core.GraalCompiler;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author yangjunnan
 * @date 2021/6/8
 */
@Slf4j
public class MyFilter extends ZuulFilter {
    //类型
    @Override
    public String filterType() {
        return null;
    }
    //执行顺序,有点像servlet
    @Override
    public int filterOrder() {
        return 0;
    }
    //是否过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }
    //执行逻辑
    @Override
    public Object run() throws ZuulException {
        //获取到每次请求的request
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headName = headerNames.nextElement();
            log.info("headName:{}, headValue:{}", headName, request.getHeader(headName));
        }
        return null;
    }
}
