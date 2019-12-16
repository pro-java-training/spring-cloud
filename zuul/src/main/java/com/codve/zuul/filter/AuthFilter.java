package com.codve.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2019/12/16 11:10
 */
@Slf4j
public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        Object success = context.get("isSuccess");
        return success == null || Boolean.parseBoolean(success.toString());
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String bearerToken = request.getHeader("Authorization");
        log.warn(bearerToken);
        if (StringUtils.hasText(bearerToken)) {
            // 鉴权
            // 如果鉴权失败
            context.setSendZuulResponse(false); // 停止调用服务
            context.setResponseBody("auth failed.");
            context.set("isSuccess", false); // 后面的拦截器就不用再执行了
            return null;
        }
        log.warn("auth passed.");
        return null;
    }
}
