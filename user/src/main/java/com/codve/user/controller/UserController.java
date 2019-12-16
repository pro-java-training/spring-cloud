package com.codve.user.controller;

import com.codve.user.annotation.Admin;
import com.codve.user.annotation.User;
import com.codve.user.convert.UserConvert;
import com.codve.user.exception.EX;
import com.codve.user.model.auth.AuthUser;
import com.codve.user.model.auth.UserType;
import com.codve.user.model.data.object.UserDO;
import com.codve.user.model.query.UserCreateQuery;
import com.codve.user.model.query.UserQuery;
import com.codve.user.model.query.UserUpdateQuery;
import com.codve.user.model.vo.UserVO;
import com.codve.user.service.UserService;
import com.codve.user.util.CommonResult;
import com.codve.user.util.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static com.codve.user.util.ExceptionUtil.exception;

/**
 * @author admin
 * @date 2019/11/21 12:19
 */
@RestController
@RequestMapping(value = "/user")
@Validated
@Api(tags = "用户接口")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("注册")
    @PostMapping("/save")
    public CommonResult save(@Validated UserCreateQuery user) {
        UserDO userDO = UserConvert.convert(user);
        userDO.setType(UserType.USER.getType());
        userService.save(userDO);
        return CommonResult.success();
    }

    @Admin
    @ApiOperation("添加管理员")
    @PostMapping("/save/admin")
    public CommonResult saveAdmin(@Validated UserCreateQuery user) {
        UserDO userDO = UserConvert.convert(user);
        userDO.setType(UserType.ADMIN.getType());
        userService.save(UserConvert.convert(user));
        return CommonResult.success();
    }

    @Admin
    @ApiOperation("删除")
    @ApiImplicitParam(name = "id", value = "用户编号", example = "1")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable @Valid @Min(value = 1) Long id) {
        userService.deleteById(id);
        return CommonResult.success();
    }

    @User
    @ApiOperation("更新")
    @PostMapping("/update")
    public CommonResult update(@Validated UserUpdateQuery updateQuery) {
        userService.update(UserConvert.convert(updateQuery));
        return CommonResult.success();
    }

    @User
    @ApiOperation("根据 id 查找")
    @ApiImplicitParam(name = "id", value = "用户编号", example = "1")
    @GetMapping("/{id}")
    public CommonResult<UserVO> findById(@PathVariable("id") @Valid @Min(value = 1) Long id) {
        UserDO user = userService.findById(id);
        return CommonResult.success(UserConvert.convert(user));
    }

    @User
    @ApiOperation("用户详情")
    @ApiImplicitParam(name = "Authorization", value = "token", paramType = "header")
    @GetMapping("/info")
    public CommonResult<UserVO> info() {
        AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDO user = userService.findById(authUser.getId());
        return CommonResult.success(UserConvert.convert(user));
    }

    @ApiOperation("根据条件搜索")
    @PostMapping("/find")
    public CommonResult<PageResult<UserVO>> find(@RequestBody @Validated UserQuery query) {
        List<UserDO> userDoList = userService.find(query);
        if (userDoList.size() == 0) {
            exception(EX.E_1104);
        }
        PageResult<UserVO> pageResult = UserConvert.convert(userDoList);
        return CommonResult.success(pageResult);
    }
}
