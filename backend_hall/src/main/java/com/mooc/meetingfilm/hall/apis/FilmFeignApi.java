package com.mooc.meetingfilm.hall.apis;

import com.mooc.meetingfilm.apis.film.FilmFeignApis;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yangjunnan
 * @date 2021/6/6
 */
@FeignClient(name = "film-service")
public interface FilmFeignApi extends FilmFeignApis {
}
