package com.codve.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author admin
 * @date 2019/12/16 15:05
 */
public class LimitFilter extends ZuulFilter {

    public static volatile RateLimiter rateLimiter = RateLimiter.create(10.0);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        rateLimiter.acquire();
        return null;
    }
}
