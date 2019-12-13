package com.codve.article.controller;

import com.codve.article.util.CommonResult;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

}
