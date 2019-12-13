package com.codve.article.controller;

import com.codve.article.client.UserClient;
import com.codve.article.model.query.UserQuery;
import com.codve.article.util.CommonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.POST;

/**
 * @author admin
 * @date 2019/12/12 19:32
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient client;

    @Autowired
    private UserClient userClient;

    @GetMapping("/auth")
    public String auth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "James");
        params.add("password", "123456");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate
                .exchange("http://user-server/auth", POST, request, String.class);
        return response.getBody();
    }

    @GetMapping("/user-server")
    public CommonResult<String> point() {
        ServiceInstance instance = client.choose("user-server");
        String info = instance.getServiceId() + " " + instance.getHost() + ":" + instance.getPort();
        return CommonResult.success(info);
    }

    @PostMapping("/user/save")
    public String save(@RequestParam(name = "name") String name, @RequestParam(name="password") String password) {
        return userClient.save(name, password);
    }

    @PostMapping("/user/save2")
    public String save2(@RequestBody UserQuery query) {
        log.warn(query.toString());
        return userClient.save2(query);
    }

    @GetMapping("/user/info")
    public String findByToken(@RequestHeader("Authorization") String token) {
        return userClient.info(token);
    }

    @HystrixCommand(fallbackMethod="authFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")},
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "3"),
                @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    @PostMapping("/user/auth")
    public String auth(@RequestParam("name") String name, @RequestParam("password") String password) {
        return userClient.auth(name, password);
    }

    public String authFallback(@RequestParam("name") String name, @RequestParam("password") String password) {
        return "暂时无法获取授权";
    }

}
