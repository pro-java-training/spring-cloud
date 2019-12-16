package com.codve.user.controller;

import com.codve.user.model.query.UserLoginQuery;
import com.codve.user.service.AuthService;
import com.codve.user.util.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2019/12/11 09:07
 */
@RestController
@Api(tags = "授权接口")
public class AuthController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    @ApiOperation("获取token")
    public CommonResult<String> auth2(HttpServletRequest request, @Validated UserLoginQuery query) {
        String token = authService.passwordAuth(request, query);
        return CommonResult.success(token);
    }
}
