package com.codve.article.client;

import com.codve.article.model.query.UserQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author admin
 * @date 2019/12/13 13:44
 */
@FeignClient(name = "user-server")
public interface UserClient {

    @PostMapping(value = "/user/save")
    String save(@RequestParam(name = "name") String username, @RequestParam(name="password") String password);

    @PostMapping(value = "/user/save")
    String save2(@RequestBody UserQuery userCreateQuery);

    @RequestMapping(value = "/user/info", method = GET)
    String info(@RequestHeader("Authorization") String token);

    @PostMapping("/auth")
    String auth(@RequestParam("name") String name, @RequestParam("password") String password);
}
