package com.codve.article.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author admin
 * @date 2019/12/16 10:10
 */
@Slf4j
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 添加拦截逻辑
        log.warn(requestTemplate.url());
    }
}
