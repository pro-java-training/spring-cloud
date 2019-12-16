package com.codve.zuul.fallback;

import com.google.common.base.Charsets;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author admin
 * @date 2019/12/16 11:51
 */
public class LocalFallbackProvider implements FallbackProvider {
    // 对所有失败的请求进行回退
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                RequestContext context = RequestContext.getCurrentContext();
                Throwable throwable = context.getThrowable();
                // 使用 CommonResult 返回统一错误
                return new ByteArrayInputStream("fallback".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(new MediaType("application/json", Charsets.UTF_8.name()));
                return headers;
            }
        };
    }
}
