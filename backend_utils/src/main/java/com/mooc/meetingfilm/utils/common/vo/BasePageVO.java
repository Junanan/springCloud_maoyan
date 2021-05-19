package com.mooc.meetingfilm.utils.common.vo;

import com.mooc.meetingfilm.utils.exception.CommonServiceException;
import jdk.internal.net.http.common.Log;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : jiangzh
 * @program : com.mooc.meetingfilm.utils.common.vo
 * @description : 分页请求类
 **/
@Slf4j
@Data
public class BasePageVO extends BaserRequestVO{
    //默认值
    private Integer nowPage = 1;
    private Integer pageSize = 10;

    @Override
    public void checkParam() throws CommonServiceException {
        if (nowPage == null || pageSize == null){
            log.error("page,size不能为空");
        }

    }
}
